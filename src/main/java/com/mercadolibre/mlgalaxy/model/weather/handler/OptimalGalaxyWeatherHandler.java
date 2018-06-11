package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeatherType;

/**
 * Optimal weather conditions type handler.
 */
public class OptimalGalaxyWeatherHandler extends GalaxyWeatherHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean apply(final Galaxy galaxy, final PositionCalculator<?> positionCalculator) {
        return positionCalculator.aligned(galaxy.getPlanets())
                        && !positionCalculator.sunAligned(galaxy.getPlanets(), galaxy.getSunCoordinates());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GalaxyWeather compute(final WeatherSimulation simulation) {
        return simulation.acumWeatherTypeDay(GalaxyWeatherType.OPTIMAL);
    }
}
