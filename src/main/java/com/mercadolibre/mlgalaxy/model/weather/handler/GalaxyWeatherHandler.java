package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;

/**
 * Base Handler of the chain of responsibility pattern for the weather galaxy forecast.
 */
public abstract class GalaxyWeatherHandler {

    private GalaxyWeatherHandler nextHandler;

    /**
     * @return the nextHandler
     */
    public final GalaxyWeatherHandler getNextHandler() {
        return nextHandler;
    }

    /**
     * @param nextHandler
     *            the nextHandler to set
     * @return {@link GalaxyWeatherHandler} Esta instancia.
     */
    public final GalaxyWeatherHandler setNextHandler(final GalaxyWeatherHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this;
    }

    /**
     * Starts the chain of responsibility pattern, if the handler apply, it takes action.
     * Otherwise, it executes the next handler.
     *
     * @param simulation
     *            {@link WeatherSimulation} The weatherSimulation being running.
     *
     * @return {@link GalaxyWeather} The weather applied by the chain of responsibility.
     */
    public GalaxyWeather execute(final WeatherSimulation simulation) {
        if (apply(simulation.getGalaxy(), simulation.getPositionCalculator())) {
            return compute(simulation);
        } else {
            return nextHandler.execute(simulation);
        }
    }

    /**
     * Checks if the handler applies to the given scenario.
     *
     * @param galaxy
     *            {@link Galaxy} The galaxy given.
     * @param positionCalculator
     *            {@link PositionCalculator} The position calculator implemented.
     *
     * @return {@link Boolean} Whether the handler applied.
     */
    protected abstract boolean apply(final Galaxy galaxy, final PositionCalculator<?> positionCalculator);

    /**
     * Perform the actual actions of the handler.
     *
     * @param simulation
     *            {@link GalaxyWeather} The weatherSimulation being running.
     * @return {@link GalaxyWeather} The weather applied by the chain of responsibility.
     */
    protected abstract GalaxyWeather compute(final WeatherSimulation simulation);
}
