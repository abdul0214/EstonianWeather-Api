package com.estonia.weatherservice.forecast.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;


/**
 * represents an embeddable Wind for Day and Night
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Data
@Embeddable
public class Wind {
    private String name;
    private String direction;
    private String speedmin;
    private String speedmax;
    private String gust;
}
