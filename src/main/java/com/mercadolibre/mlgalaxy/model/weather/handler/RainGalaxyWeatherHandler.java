package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.Planet;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeatherType;

import java.util.List;

/**
 *
 * Rain weather type handler
 *
 * This particular handler needs to compute the perimeter of the triangle
 * conformed by the planet's positions.
 */
public class RainGalaxyWeatherHandler extends GalaxyWeatherHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean apply(final Galaxy galaxy, final PositionCalculator<?> positionCalculator) {
        if (!positionCalculator.aligned(galaxy.getPlanets())) {
            return positionCalculator.planetTriangleContainsTheSun(galaxy.getSunCoordinates(), galaxy.getPlanets());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GalaxyWeather compute(final WeatherSimulation simulation) {
        simulation.getPerimeter(perimeter(simulation.getGalaxy()));
        return simulation.acumWeatherTypeDay(GalaxyWeatherType.RAIN);
    }

    /**
     * Calculates the perimeter of the triangle conformed by the planet's positions.
     *
     * @param galaxy
     *            {@link Galaxy} Galaxy given.
     * @return {@link Double} Perimeter.
     */
    protected double perimeter(final Galaxy galaxy) {
        int i = 0;
        double perimeter = 0d;
        final List<Planet> planets = galaxy.getPlanets();
        while (i < planets.size() - 1) {
            perimeter += planets.get(i).distance(planets.get(i + 1));
            i++;
        }
        perimeter += planets.get(0).distance(planets.get(planets.size() - 1));
        return perimeter;
    }
}
