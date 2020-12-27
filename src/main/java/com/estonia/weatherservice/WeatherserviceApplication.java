package com.estonia.weatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherserviceApplication.class, args);
    }

}
