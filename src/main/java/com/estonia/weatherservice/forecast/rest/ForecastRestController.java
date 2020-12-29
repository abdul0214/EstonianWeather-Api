package com.estonia.weatherservice.forecast.rest;


import com.estonia.weatherservice.exception.types.ResourceNotFoundException;
import com.estonia.weatherservice.forecast.application.dto.ForecastDTO;
import com.estonia.weatherservice.forecast.application.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Endpoints controller
 * for Forecast Package
 * Utilizes {@link ForecastService}
 * More End-points to be added
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://awayoffice.web.app"})
@RequestMapping("/api/estonian")
public class ForecastRestController {

    @Autowired
    ForecastService forecastService;

    /**
     * end-point method for getting all Forecasts
     *
     * @return List<ForecastDTO>
     * @author Abdul Wahab
     * @since 1.0
     */
    @GetMapping("/forecasts")
    public ResponseEntity<List<ForecastDTO>> getAllForecasts() throws Exception {

        List<ForecastDTO> forecasts = forecastService.getAllForecasts();
        return new ResponseEntity<>(forecasts, HttpStatus.OK);
    }

    /**
     * end-point method for getting an Forecast by id
     *
     * @param date - date of Forecast to be fetched
     * @return ForecastDTO
     * @author Abdul Wahab
     * @since 1.0
     */
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
