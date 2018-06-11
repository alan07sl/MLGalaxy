package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeatherType;

/**
 * Default handler.
 * The pattern needs this default handler at the end of the chain.
 */
public class RegularGalaxyWeatherHandler extends GalaxyWeatherHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean apply(final Galaxy galaxy, final PositionCalculator<?> positionCalculator) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GalaxyWeather compute(final WeatherSimulation simulation) {
        return simulation.acumWeatherTypeDay(GalaxyWeatherType.REGULAR);
    }
}
