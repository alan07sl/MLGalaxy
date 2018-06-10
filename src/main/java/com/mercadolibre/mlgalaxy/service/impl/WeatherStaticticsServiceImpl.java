package com.mercadolibre.mlgalaxy.service.impl;

import com.mercadolibre.mlgalaxy.model.statictics.WeatherStatictics;
import com.mercadolibre.mlgalaxy.repository.GalaxyWeatherRepository;
import com.mercadolibre.mlgalaxy.repository.WeatherStaticticsRepository;
import com.mercadolibre.mlgalaxy.service.WeatherStaticticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to access the statictics.
 *
 * Right now we just have one galaxy but if in the future we discover new galaxies and we want to extend the system
 * to get statictics including data from other galaxies we will be glad of having the statictics services not mixed
 * with our galaxy's weather services.
 */
@Service
public class WeatherStaticticsServiceImpl implements WeatherStaticticsService{

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherStaticticsServiceImpl.class);

    @Autowired
    private GalaxyWeatherRepository galaxyWeatherRepository;

    @Autowired
    private WeatherStaticticsRepository weatherStaticticsRepository;

    /**
     * Gets the weather statictics.
     *
     * @return {@link WeatherStatictics}
     */
    public Optional<WeatherStatictics> getStatictics() {
        return Optional.ofNullable(weatherStaticticsRepository.findAll().iterator().next());
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
