package com.estonia.weatherservice.forecast.application.service;

import com.estonia.weatherservice.forecast.application.dto.ForecastDTO;
import com.estonia.weatherservice.forecast.domain.model.Forecast;
import com.estonia.weatherservice.forecast.domain.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastService {

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private ForecastAssembler forecastAssembler;

    @Value("${dateformat}")
    private String dateformat;


    /**
     * this is the method for creating
     * all Forecasts in the database
     *
     * @param forecasts - list of Forecasts
     * @since 1.0
     */
    public void createForecasts(Forecast[] forecasts) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateformat);
        String dateTomorrow = dtf.format(LocalDateTime.now().plusDays(1));
        if (forecastRepository.existsForecastByDate(dateTomorrow)) {
            forecastRepository.deleteByDate(dateTomorrow);
        }
        forecastRepository.saveAll(Arrays.asList(forecasts));
    }

    /**
     * this is the method for returning
     * all Forecasts in the database
     *
     * @return List<ForecastDTO>
     * @since 1.0
     */
    public List<ForecastDTO> getAllForecasts() throws Exception {

        List<Forecast> forecasts = forecastRepository.findAll();
        return forecasts.stream().map(forecast -> forecastAssembler.toModel(forecast)).collect(Collectors.toList());

    }

    /**
     * this is the method for fetching
     * a Forecast instance by date
     * *
     *
     * @param date - date of the forecast
     * @return ForecastDTO
     * @since 1.0
     */
    public ForecastDTO getForecastByDate(String date) {
        Forecast forecast = forecastRepository.findByDate(date);
        System.out.println(forecast);
        if (forecast != null) {
            return forecastAssembler.toModel(forecast);
        } else {
            return null;
        }
    }

    /**
     * this is the method for deleting
     * a Forecast instance by date
     * *
     *
     * @param date - date of the forecast
     * @return ForecastDTO
     * @since 1.0
     */
    public ForecastDTO deleteForecastByDate(String date) {
        Forecast forecast = forecastRepository.findByDate(date);
        if (forecast != null) {
            return null;
        } else {
            forecastRepository.deleteByDate(date);
            return forecastAssembler.toModel(forecast);
        }
    }

    /**
     * this is the method for updating
     * a Forecast
     * *
     *
     * @param forecastUpdated - a forecast
     * @return ForecastDTO
     * @since 1.0
     */
    public ForecastDTO updateForecast(Forecast forecastUpdated) {
        Forecast forecast = forecastRepository.findByDate(forecastUpdated.getDate());
        forecast.setDay(forecastUpdated.getDay());
        forecast.setNight(forecastUpdated.getNight());
        forecastRepository.save(forecast);
        if (forecast != null) {
            return forecastAssembler.toModel(forecast);
        } else {
            return null;
        }
    }
}
