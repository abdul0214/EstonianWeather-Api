package com.estonia.weatherservice.forecast.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * represents a Place for Night
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Data
@Embeddable
public class PlaceNight {
    private String name;
    private String phenomenon;
    private String tempmin;
}
