package com.mercadolibre.mlgalaxy.model.weather.handler;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import com.mercadolibre.mlgalaxy.model.position.strategy.impl.SquaredRStrategy;
import com.mercadolibre.mlgalaxy.model.weather.handler.DroughtGalaxyWeatherHandler;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DroughtGalaxyWeatherHandlerTest {

    private final DroughtGalaxyWeatherHandler droughtHandler = new DroughtGalaxyWeatherHandler();
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
    public void notAlignedTest() {
        when(positionCalculator.sunAligned(anyList(), any(Point2D.Double.class))).thenReturn(false);
        assertFalse(droughtHandler.apply(galaxy, positionCalculator));
    }
}
