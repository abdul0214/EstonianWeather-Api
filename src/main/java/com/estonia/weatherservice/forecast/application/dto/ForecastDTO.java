package com.estonia.weatherservice.forecast.application.dto;

import com.estonia.weatherservice.forecast.domain.model.Day;
import com.estonia.weatherservice.forecast.domain.model.Night;
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

    Day day;

    Night night;

    String date;
}
