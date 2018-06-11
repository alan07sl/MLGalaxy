package com.mercadolibre.mlgalaxy.model.position.strategy.impl;

import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import com.mercadolibre.mlgalaxy.model.position.strategy.PositionStrategy;

import java.awt.geom.Point2D;

/**
 * Squared R (x,y) strategy implementation {@link PositionStrategy} by: {@link Point2D.Double} and
 * {@link PositionCalculatorImpl}.
 */
public class SquaredRStrategy implements PositionStrategy<Point2D.Double, PositionCalculatorImpl> {

    /**
     * {@inheritDoc}
     */
    @Override
    public PositionCalculatorImpl getPositionCalculator() {
        return new PositionCalculatorImpl();
    }
}
