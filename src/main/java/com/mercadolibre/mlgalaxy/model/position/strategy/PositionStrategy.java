package com.mercadolibre.mlgalaxy.model.position.strategy;

import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;

import java.awt.geom.Point2D;

/**
 * Strategy pattern interface to implement multiple position calculation
 * strategies.
 *
 * @param <T>
 *            Type of point used.
 * @param <C>
 *            Type of calculator used.
 */
public interface PositionStrategy<T extends Point2D, C extends PositionCalculator<T>> {

    /**
     * Get the position calculator {@link PositionCalculator} to be used.
     *
     * @return {@link PositionCalculator}.
     */
    C getPositionCalculator();
}
