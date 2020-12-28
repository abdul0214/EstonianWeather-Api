package com.estonia.weatherservice.forecast.domain.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a Day
 * referenced by day_id  in Forecast
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Data
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    private String phenomenon;
    private String tempmin;
    private String tempmax;

    @Column(length = 10000)
    private String text;

    @Column(length = 10000)
    private String sea;

    @Column(length = 10000)
    private String peipsi;


    @Column
    @ElementCollection(targetClass = Wind.class)
    private List<Wind> winds;

    @Column
    @ElementCollection(targetClass = PlaceDay.class)
    private List<PlaceDay> places;


    @JsonSetter("wind")
    public void setWinds(Wind wind) {
        if (this.winds == null) {
            this.winds = new ArrayList<Wind>();
        }
        winds.add(wind);
    }

    @JsonSetter("place")
    public void setPlaces(PlaceDay place) {
        if (this.places == null) {
            this.places = new ArrayList<PlaceDay>();
        }
        places.add(place);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forecast_id", referencedColumnName = "id")
    private Forecast forecast;

}