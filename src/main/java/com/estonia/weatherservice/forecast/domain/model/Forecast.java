package com.estonia.weatherservice.forecast.domain.model;

import com.fasterxml.jackson.annotation.JsonSetter;
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
@Table(name = "Forecasts")
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
    public void setDayData(Day value) {
        this.day = value.toString();
    }

    @JsonSetter("night")
    public void setNightData(Night value) {
        this.night = value.toString();
    }


}
