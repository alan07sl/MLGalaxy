package com.mercadolibre.mlgalaxy.job;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import com.mercadolibre.mlgalaxy.model.position.strategy.impl.SquaredRStrategy;
import com.mercadolibre.mlgalaxy.model.weather.statictics.WeatherStatictics;
import com.mercadolibre.mlgalaxy.service.GalaxyService;
import com.mercadolibre.mlgalaxy.service.GalaxyWeatherService;
import com.mercadolibre.mlgalaxy.service.WeatherStaticticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job scheduled on the application start.
 */
@Component
public class ForecastScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastScheduler.class);

    private static final int A_YEAR = 365;
    private static final int YEAR_TO_BE_SIMULATED = 10;

    @Autowired
    private GalaxyWeatherService galaxyWeatherService;

    @Autowired
    private GalaxyService galaxyService;

    @Autowired
    private WeatherStaticticsService weatherStaticticsService;

    /**
     * Cleans the data and generates the forecast for the next ten years
     * Runs just on the start of the application.
     */
    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public final void forecastSimulate() {
        galaxyService.cleanData();
        galaxyWeatherService.cleanData();
        weatherStaticticsService.cleanData();

        final Galaxy galaxy = new Galaxy(new PositionCalculatorImpl());
        galaxy.addPlanet("Ferengi",  1, 500, true, 0, 500);
        galaxy.addPlanet("Betasoide",  3, 2000, true, 0, 2000);
        galaxy.addPlanet("Vulcano",  5, 1000, false, 0, 1000);
        galaxyService.save(galaxy);

        LOGGER.info(String.format("Forecasting the next %s years", YEAR_TO_BE_SIMULATED));
        final WeatherStatictics statictics = new WeatherSimulation(galaxy, new SquaredRStrategy()).simulateUntil(A_YEAR * YEAR_TO_BE_SIMULATED);
        weatherStaticticsService.save(statictics);
        LOGGER.info("Forecast complete");
    }
}
