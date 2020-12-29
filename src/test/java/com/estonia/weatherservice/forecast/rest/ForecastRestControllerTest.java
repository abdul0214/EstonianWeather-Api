package com.estonia.weatherservice.forecast.rest;


import com.estonia.weatherservice.background.application.service.WeatherDataUpdateService;
import com.estonia.weatherservice.forecast.application.dto.ForecastDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForecastRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ForecastRestController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WeatherDataUpdateService weatherDataUpdateService;


    @Value("${dateformat}")
    private String dateformat;

    @Before
    public void updateData() throws Exception {
        weatherDataUpdateService.updateWeatherData();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getForTomorrowWeatherIsOK() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateformat);
        String dateTomorrow = dtf.format(LocalDateTime.now().plusDays(1));
        String url = "http://localhost:" + port + "/api/estonian/forecast/" + dateTomorrow;
        ResponseEntity<ForecastDTO> response = this.restTemplate.getForEntity(url, ForecastDTO.class);
        response = this.restTemplate.getForEntity(url, ForecastDTO.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode() == (HttpStatus.OK));
        assertThat(response.getBody().getDate()).isEqualTo(dateTomorrow);
    }

    @Test
    public void appropriateExceptionForFifthDayForecastRequest() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateformat);
        String fifthDay = dtf.format(LocalDateTime.now().plusDays(5));
        String url = "http://localhost:" + port + "/api/estonian/forecast/" + fifthDay;
        ResponseEntity<ForecastDTO> response = this.restTemplate.getForEntity(url, ForecastDTO.class);
        response = this.restTemplate.getForEntity(url, ForecastDTO.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode() == (HttpStatus.NOT_FOUND));
    }
}

