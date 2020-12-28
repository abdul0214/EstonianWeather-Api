package com.estonia.weatherservice.forecast.application.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * DTO representation of Forecast
 * for transfer of Forecast
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Data
public class ForecastDTO extends RepresentationModel<ForecastDTO> {

    String day;

    String night;

    String date;
}
