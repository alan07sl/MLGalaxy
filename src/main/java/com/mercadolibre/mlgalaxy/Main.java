package com.mercadolibre.mlgalaxy;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import com.mercadolibre.mlgalaxy.model.position.strategy.impl.SquaredRStrategy;

/**
 * Main class to run the system simulation without spring-boot.
 */
public class Main {

    private static final int A_YEAR = 365;
    private static final int YEAR_TO_BE_SIMULATED = 10;

    /**
     * Main method.
     *
     * @param args
     *            {@link String[]} parameters.
     */
    public static void main(final String[] args) {

        //Create a galaxy with a position calculator.
        final Galaxy galaxy = new Galaxy(new PositionCalculatorImpl());

        //The starting positions are assumed from the graphic representation of the problem.
        galaxy.addPlanet("Ferengi",  1, 500, true, 0, 500);
        galaxy.addPlanet("Betasoide",  3, 2000, true, 0, 2000);
        galaxy.addPlanet("Vulcano", 5, 1000, false, 0, 1000);

        //Generate a simulation with the previously generated galaxy and a squared R strategy.
        final WeatherSimulation simulation = new WeatherSimulation(galaxy, new SquaredRStrategy());
        //Simulate over the time given
        simulation.simulateUntil(A_YEAR * YEAR_TO_BE_SIMULATED);

        //Print results of weather type occurrences
        simulation.getWeatherQuantityMap().keySet().forEach(eg -> {
            System.out.println(String.format("Hay %d dias de %s", simulation.getWeatherQuantityMap().get(eg), eg));
        });

        //Print max rain day
        System.out.println(String.format("Pico maximo de lluvia, dia %d", simulation.getMaxRainDay()));
    }
}
