package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.Planet;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import com.mercadolibre.mlgalaxy.model.position.strategy.impl.SquaredRStrategy;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RainGalaxyWeatherHandlerTest {

    private final RainGalaxyWeatherHandler rainGalaxyWeatherHandler = new RainGalaxyWeatherHandler();
    private final Galaxy galaxyMock = mock(Galaxy.class);
    private SquaredRStrategy squaredRStrategy;
    private PositionCalculatorImpl positionCalculator;

    @Before
    public void setup() {
        positionCalculator = mock(PositionCalculatorImpl.class);
        squaredRStrategy = mock(SquaredRStrategy.class);
        when(squaredRStrategy.getPositionCalculator()).thenReturn(positionCalculator);
    }

    @Test
    public void sunNotContainedTest() {
        when(positionCalculator.aligned(anyList())).thenReturn(false);
        when(positionCalculator.planetTriangleContainsTheSun(any(Point2D.Double.class), anyList())).thenReturn(false);
        assertFalse(rainGalaxyWeatherHandler.apply(galaxyMock, positionCalculator));
    }

    @Test
    public void planetsNotAlignedTest() {
        when(positionCalculator.aligned(anyList())).thenReturn(false);
        assertFalse(rainGalaxyWeatherHandler.apply(galaxyMock, positionCalculator));
    }

    @Test
    public void perimeterTest() {
        final Galaxy galaxy = new Galaxy();
        final List<Planet> planets = new ArrayList<>();
        final Planet p1 = new Planet("Planet 1", 1, 1, Boolean.TRUE, new Point2D.Double(0d, 2d));
        final Planet p2 = new Planet("Planet 2", 1, 1, Boolean.TRUE, new Point2D.Double(0d, -2d));
        final Planet p3 = new Planet("Planet 3", 1, 1, Boolean.TRUE, new Point2D.Double(2d, 0d));
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);
        galaxy.getPlanets().addAll(planets);
        assertEquals(Math.sqrt(8) * 2 + 4, rainGalaxyWeatherHandler.perimeter(galaxy), 0d);
    }
}
