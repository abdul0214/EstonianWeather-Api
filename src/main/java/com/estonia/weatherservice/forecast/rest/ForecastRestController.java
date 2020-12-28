package com.estonia.weatherservice.forecast.rest;


import com.estonia.weatherservice.exception.types.NoContentException;
import com.estonia.weatherservice.exception.types.ResourceNotFoundException;
import com.estonia.weatherservice.forecast.application.dto.ForecastDTO;
import com.estonia.weatherservice.forecast.application.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estonian")
public class ForecastRestController {

    @Autowired
    ForecastService forecastService;


    @GetMapping("/forecasts")
    public ResponseEntity<List<ForecastDTO>> getAllForecasts() throws Exception {
        List<ForecastDTO> forecasts = forecastService.getAllForecasts();
        if (forecasts.isEmpty()) {
            throw new NoContentException("No  Forecasts Found");
        } else {
            return new ResponseEntity<>(forecasts, HttpStatus.OK);
        }
    }

    @GetMapping("/forecast/{date}")
    public ResponseEntity<ForecastDTO> getForecastByDate(@PathVariable("date") String date) {
        ForecastDTO forecast = forecastService.getForecastByDate(date);
        if (forecast == null) {
            throw new ResourceNotFoundException("No Forecast Found by date: " + date);
        } else {
            return new ResponseEntity<>(forecast, HttpStatus.OK);
        }
    }
}
