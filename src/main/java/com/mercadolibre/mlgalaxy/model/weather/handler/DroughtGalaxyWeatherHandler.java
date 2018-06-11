package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeatherType;

/**
 * Drought weather type handler.
 *
 */
public class DroughtGalaxyWeatherHandler extends GalaxyWeatherHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean apply(final Galaxy galaxy, final PositionCalculator<?> positionCalculator) {
        return positionCalculator.sunAligned(galaxy.getPlanets(), galaxy.getSunCoordinates());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GalaxyWeather compute(final WeatherSimulation simulation) {
        return simulation.acumWeatherTypeDay(GalaxyWeatherType.DROUGHT);
    }
}
