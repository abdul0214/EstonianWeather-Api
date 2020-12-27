package com.estonia.weatherservice.forecast.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;


/**
 * represents a Place for Day
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Data
@Embeddable
public class PlaceDay {
    private String name;
    private String phenomenon;
    private String tempmax;
}
