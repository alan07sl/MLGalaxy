package com.mercadolibre.mlgalaxy.service.impl;

import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.repository.GalaxyWeatherRepository;
import com.mercadolibre.mlgalaxy.service.GalaxyWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to access the galaxy's weather data.
 *
 */
@Service
public class GalaxyWeatherServiceImpl implements GalaxyWeatherService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GalaxyWeatherServiceImpl.class);

    @Autowired
    private GalaxyWeatherRepository galaxyWeatherRepository;

    /**
     * Gets the weather for a specific day.
     *
     * @param day
     *            {@link Integer} The specific day.
     * @return {@link GalaxyWeather} Weather for that specific day.
     */
    public Optional<GalaxyWeather> weatherByDay(final Integer day) {
        LOGGER.info("Searching by day {}", day);
        return galaxyWeatherRepository.findByDay(day);
    }

    /**
     * Persists the galaxy weather.
     *
     * @param galaxyWeather
     *            {@link GalaxyWeather} Statictics to be persisted.
     */
    public void save(final GalaxyWeather galaxyWeather) {
        galaxyWeatherRepository.save(galaxyWeather);
    }

    /**
     * Cleans galaxy weather registries.
     */
    public void cleanData() {
        LOGGER.info("Cleaning weather data");
        galaxyWeatherRepository.deleteAll();
    }


}
