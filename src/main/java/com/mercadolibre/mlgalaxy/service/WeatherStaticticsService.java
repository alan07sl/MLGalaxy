package com.mercadolibre.mlgalaxy.service;

import com.mercadolibre.mlgalaxy.model.weather.WeatherStatictics;
import com.mercadolibre.mlgalaxy.repository.GalaxyWeatherRepository;
import com.mercadolibre.mlgalaxy.repository.WeatherStaticticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to access the statictics.
 *
 * Right now we just have one galaxy but if in the future we discover new galaxies and we want to extend the system
 * to get statictics including data from other galaxies we will be glad of having the statictics services not mixed
 * with our galaxy's weather services.
 */
@Service
public class WeatherStaticticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherStaticticsService.class);

    @Autowired
    private GalaxyWeatherRepository galaxyWeatherRepository;

    @Autowired
    private WeatherStaticticsRepository weatherStaticticsRepository;

    /**
     * Gets the weather statictics.
     *
     * @return {@link WeatherStatictics}
     */
    public WeatherStatictics getStatictics() {
        return weatherStaticticsRepository.findAll().iterator().next();
    }

    /**
     * Persists the statictics.
     *
     * @param statictics
     *            {@link WeatherStatictics} Statictics to be persisted.
     */
    public void save(final WeatherStatictics statictics) {
        weatherStaticticsRepository.save(statictics);
    }

    /**
     * Cleans weather registries.
     */
    public void cleanData() {
        LOGGER.info("Limpiando datos de clima");
        galaxyWeatherRepository.deleteAll();
        weatherStaticticsRepository.deleteAll();
    }

}
