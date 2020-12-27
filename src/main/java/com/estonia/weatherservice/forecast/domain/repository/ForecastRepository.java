package com.estonia.weatherservice.forecast.domain.repository;

import com.estonia.weatherservice.forecast.domain.model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * {@link Repository} to access {@link Forecast} instances.
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    /**
     * Returns the  {@link Forecast}  with the given date.
     * We store Date as String for now for easier access
     *
     * @param date
     * @return {@link Forecast}
     * @version 1.0
     * @since 1.0
     */
    Forecast findByDate(String date);

}
