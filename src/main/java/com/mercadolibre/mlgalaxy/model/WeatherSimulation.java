package com.mercadolibre.mlgalaxy.model;

import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;
import com.mercadolibre.mlgalaxy.model.position.strategy.PositionStrategy;
import com.mercadolibre.mlgalaxy.model.weather.statictics.WeatherStatictics;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeatherType;
import com.mercadolibre.mlgalaxy.model.weather.handler.WeatherHandlerChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Simulation model class of the galaxy weather forecast.
 */
public class WeatherSimulation {

    private final Galaxy galaxy;
    private double maxPerimeter = Double.MIN_VALUE;
    private Integer maxRainDay = 0;
    private int actualDay = 0;
    private final Map<GalaxyWeatherType, Integer> WeatherQuantityMap = new HashMap<>();
    private static final WeatherHandlerChain WEATHER_HANDLER_CHAIN = new WeatherHandlerChain();
    private final PositionCalculator<?> positionCalculator;

    /**
     * Constructor.
     *
     * @param galaxy
     *            {@link Galaxy} Galaxy subject of the simulation.
     * @param positionStrategy
     *            {@link PositionStrategy} Position strategy to be applied.
     */
    public WeatherSimulation(final Galaxy galaxy, final PositionStrategy<?, ?> positionStrategy) {
        this.galaxy = galaxy;
        positionCalculator = positionStrategy.getPositionCalculator();
        Stream.of(GalaxyWeatherType.values()).forEach(e -> WeatherQuantityMap.put(e, 0));
    }

    /**
     * @return the WeatherQuantityMap
     */
    public final Map<GalaxyWeatherType, Integer> getWeatherQuantityMap() {
        return WeatherQuantityMap;
    }

    /**
     * @return the maxRainDay
     */
    public final Integer getMaxRainDay() {
        return maxRainDay;
    }

    /**
     * @return the actualDay
     */
    public int getActualDay() {
        return actualDay;
    }

    /**
     * @return the maxPerimeter
     */
    public double getMaxPerimeter() {
        return maxPerimeter;
    }

    /**
     * @return the galaxy
     */
    public Galaxy getGalaxy() {
        return galaxy;
    }

    /**
     * @return the positionCalculator
     */
    public PositionCalculator<?> getPositionCalculator() {
        return positionCalculator;
    }

    /**
     *
     * Performs the simulation until days has occurred.
     *
     * @param days
     *            {@link Integer} How much days will be simulated.
     * @return {@link WeatherStatictics} The simulation result.
     */
    public WeatherStatictics simulateUntil(final int days) {
        final List<GalaxyWeather> weathers = IntStream.range(0, days) //
                        .mapToObj(i -> dayIncrement()) //
                        .collect(Collectors.toList());
        return new WeatherStatictics(maxRainDay, weathers);
    }

    /**
     * Increments a day, refreshes the planet's positions and forecasts the weather
     * for the new day and position.
     *
     * @return {@link GalaxyWeather} The weather forecast for the new day
     *         and positions.
     */
    private GalaxyWeather dayIncrement() {
        actualDay++;
        positionsRefresh();
        return forecastWeather();
    }

    /**
     * Refresh the position of the planets on the galaxy for this simulation.
     */
    protected void positionsRefresh() {
        galaxy.getPlanets().forEach(p -> p.setPosition(
                        positionCalculator.calculatePosition(actualDay, p.getAngularVelocity(), p.getDistanceToSun())));
    }

    /**
     * Delegates the weather forecast to the weather handler chain.
     *
     * @return {@link GalaxyWeather} The weather forecast.
     */
    private GalaxyWeather forecastWeather() {
        return WEATHER_HANDLER_CHAIN.execute(this);
    }

    /**
     * Accumulates the weather occurred {@link GalaxyWeather}.
     *
     * @param weatherType
     *            {@link GalaxyWeatherType} Weather type occurred.
     * @return {@link GalaxyWeather} The weather generated.
     */
    public GalaxyWeather acumWeatherTypeDay(final GalaxyWeatherType weatherType) {
        WeatherQuantityMap.compute(weatherType, (k, v) -> v + 1);
        return new GalaxyWeather(weatherType, actualDay);
    }

    /**
     * Saves the max historical perimeter with its corresponding day.
     *
     * @param perimeter
     *            {@link Double} the perimeter to be computed.
     */
    public void getPerimeter(final double perimeter) {
        if (perimeter > maxPerimeter) {
            maxPerimeter = perimeter;
            maxRainDay = actualDay;
        }
    }
}
