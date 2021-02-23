package com.estonia.weatherservice.forecast.domain.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.*;

/**
 * represents a Forecast
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Entity
@Data
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String date;

    @Column(length = 100000)
    private String day;

    @Column(length = 100000)
    private String night;

    @JsonSetter("day")
    public void setDayData(Day value) throws JsonProcessingException {
        this.day = new ObjectMapper().writeValueAsString(value);

    }

    @JsonSetter("night")
    public void setNightData(Night value) throws JsonProcessingException {
        this.night = new ObjectMapper().writeValueAsString(value);
    }





}
