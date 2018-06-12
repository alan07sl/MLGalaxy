package com.mercadolibre.mlgalaxy;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.WeatherSimulation;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import com.mercadolibre.mlgalaxy.model.position.strategy.impl.SquaredRStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class to run the system simulation without spring-boot.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


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
        simulation.getWeatherQuantityMap().keySet().forEach(eg ->
            LOGGER.info("Hay {} dias de {}", simulation.getWeatherQuantityMap().get(eg), eg)
        );

        //Print max rain day
        LOGGER.info("Pico maximo de lluvia, dia {}", simulation.getMaxRainDay());
    }
}
