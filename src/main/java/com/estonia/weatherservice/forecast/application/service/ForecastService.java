package com.estonia.weatherservice.forecast.application.service;

import com.estonia.weatherservice.forecast.application.dto.ForecastDTO;
import com.estonia.weatherservice.forecast.domain.model.Forecast;
import com.estonia.weatherservice.forecast.domain.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastService {

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private ForecastAssembler forecastAssembler;


    /**
     * this is the method for creating
     * all Forecasts in the database
     *
     * @param forecasts - list of Forecasts
     * @since 1.0
     */
    public void createForecasts(Forecast[] forecasts) {
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
        if (forecasts.isEmpty()) {
            return null;
        } else {
            try {
                return forecasts.stream().map(forecast -> forecastAssembler.toModel(forecast)).collect(Collectors.toList());
            } catch (Exception e) {
                throw new Exception("Something Went Wrong, try again later");
            }
        }
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
        if (forecast != null) {
            return forecastAssembler.toModel(forecast);
        } else {
            return null;
        }
    }
}
