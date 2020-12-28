package com.estonia.weatherservice.forecast.application.service;

import com.estonia.weatherservice.forecast.application.dto.ForecastDTO;
import com.estonia.weatherservice.forecast.domain.model.Forecast;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class ForecastAssembler extends RepresentationModelAssemblerSupport<Forecast, ForecastDTO> {
    public ForecastAssembler() {
        super(ForecastService.class, ForecastDTO.class);
    }

    /**
     * method for creating
     * Data transfer object(DTO)
     * of Forecast object
     *
     * @return {@link ForecastDTO}
     */
    @Override
    public ForecastDTO toModel(Forecast entity) {
        ForecastDTO dto = createModelWithId(entity.getId(), entity);
        dto.setDay(entity.getDay());
        dto.setNight(entity.getNight());
        dto.setDate(entity.getDate());
        return dto;
    }
}
