package com.mercadolibre.mlgalaxy.repository;

import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * CRUD Repository used for the galaxy weather. {@link GalaxyWeather}
 */
public interface GalaxyWeatherRepository extends CrudRepository<GalaxyWeather, Integer> {

    /**
     * Finds the weather on the specified day.
     *
     * @param day
     *            {@link Integer} Specified day.
     * @return {@link GalaxyWeather} Weather of that specific day.
     */

    Optional<GalaxyWeather> findByDay(final Integer day);
}
