package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import com.mercadolibre.mlgalaxy.model.position.strategy.impl.SquaredRStrategy;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OptimalGalaxyWeatherHandlerTest {

    private final OptimalGalaxyWeatherHandler optimalGalaxyWeatherHandler = new OptimalGalaxyWeatherHandler();
    private final Galaxy galaxy = mock(Galaxy.class);
    private SquaredRStrategy squaredRStrategy;
    private PositionCalculatorImpl positionCalculator;

    @Before
    public void setup() {
        positionCalculator = mock(PositionCalculatorImpl.class);
        squaredRStrategy = mock(SquaredRStrategy.class);
        when(squaredRStrategy.getPositionCalculator()).thenReturn(positionCalculator);
    }

    @Test
    public void planetsAndSunAlignedTest() {
        when(positionCalculator.aligned(anyList())).thenReturn(true);
        when(positionCalculator.sunAligned(anyList(), any(Point2D.Double.class))).thenReturn(true);
        assertFalse(optimalGalaxyWeatherHandler.apply(galaxy, positionCalculator));
    }

    @Test
    public void planetsNotAlignedTest() {
        //This test has a scenario that's not even possible in the plane.
        when(positionCalculator.aligned(anyList())).thenReturn(false);
        when(positionCalculator.sunAligned(anyList(), any(Point2D.Double.class))).thenReturn(true);
        assertFalse(optimalGalaxyWeatherHandler.apply(galaxy, positionCalculator));
    }

    @Test
    public void planetsAndSunNotAligned() {
        when(positionCalculator.aligned(anyList())).thenReturn(false);
        when(positionCalculator.sunAligned(anyList(), any(Point2D.Double.class))).thenReturn(false);
        assertFalse(optimalGalaxyWeatherHandler.apply(galaxy, positionCalculator));
    }
}
