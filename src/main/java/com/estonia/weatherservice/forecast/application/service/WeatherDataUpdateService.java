package com.estonia.weatherservice.forecast.application.service;

import com.estonia.weatherservice.forecast.domain.model.Forecast;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

/**
 * Service for Scheduled Weather Data Update
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Service
public class WeatherDataUpdateService {

    @Autowired
    ForecastService forecastService;

    @Value("${weatherDataURL}")
    private String weatherDataURL;

    @Value("${userAgent}")
    private String userAgent;

    @Value("${userAgentBrowser}")
    private String userAgentBrowser;

    @Value("${encodingStandard}")
    private String encodingStandard;


    /**
     * Principal Method of the class;
     * Scheduled Routine
     * for fetching Weather data XML from URL
     * URL sourced from application.properties
     * Entire XML Data is mapped at once on model and
     * stored in DB as day based Forecast list
     * Following Encoding for XML Datta : ISO 8859-4
     * according to: https://en.wikipedia.org/wiki/Character_encoding
     * cron based scheduler
     * for updating at 00:00 hours Eastern European Time(EET)
     * fixedRate based is  only for demo purposes
     *
     * @author Abdul Wahab
     * @since 1.0
     */
//    @Scheduled(cron = "0 0 0 * * *",zone = "Europe/Tallinn")
    @Scheduled(fixedRate = 3600 * 24 * 1000)
    public void updateExchangeRate() throws IOException {
        try {
            String xmlString = fetchXMLExchangeRateData(weatherDataURL);
            XmlMapper xmlMapper = new XmlMapper();
            Forecast[] forecasts
                    = xmlMapper.readValue(xmlString, Forecast[].class);
            forecastService.createForecasts(forecasts);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the XML Data from
     * the given URL
     * as a String
     *
     * @param xmlURL - URL String
     * @return {@link String}
     * @since 1.0
     */
    public String fetchXMLExchangeRateData(String xmlURL) throws Exception {
        URL url = new URL(xmlURL);
        URLConnection yc = url.openConnection();
        yc.setRequestProperty(userAgent, userAgentBrowser);
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), encodingStandard));
        String xmlString = in.lines().collect(Collectors.joining());
        in.close();
        return xmlString;
    }
}
