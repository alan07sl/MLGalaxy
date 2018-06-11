package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;

/**
 * Base class which will launch the chain of responsibility for the weather galaxy forecast.
 *
 */
public class WeatherHandlerChain {

    private final GalaxyWeatherHandler firstHandler;

    /**
     * Constructor. Creates the chain with all the actual handlers <br>
     * 1- {@link DroughtGalaxyWeatherHandler} <br>
     * 2- {@link RainGalaxyWeatherHandler} <br>
     * 3- {@link OptimalGalaxyWeatherHandler} <br>
     * 4- {@link RegularGalaxyWeatherHandler}
     *
     * In case of more weathers to be included please add the corresponding
     * handler here, but the RegularGalaxyWeatherHandler must be the last handler to
     * be considered since it is the default case.
     */
    public WeatherHandlerChain() {
        firstHandler = new DroughtGalaxyWeatherHandler();
        firstHandler.setNextHandler(
                        new RainGalaxyWeatherHandler().setNextHandler(new OptimalGalaxyWeatherHandler().setNextHandler(new RegularGalaxyWeatherHandler())));
    }

    /**
     * Launches the handler execution chain.
     *
     * @param simulation
     *            {@link WeatherSimulation} The weatherSimulation being running.
     * @return {@link GalaxyWeather} The weather applied by the chain of responsibility.
     */
    public GalaxyWeather execute(final WeatherSimulation simulation) {
        return firstHandler.execute(simulation);
    }
}
