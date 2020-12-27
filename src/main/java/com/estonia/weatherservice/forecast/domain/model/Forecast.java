package com.estonia.weatherservice.forecast.domain.model;

import lombok.Data;

import javax.persistence.*;


/**
 * represents a Forecast
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Data
@Entity
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "night_id", referencedColumnName = "id")
    private Night night;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    private Day day;

    private String date;

}
