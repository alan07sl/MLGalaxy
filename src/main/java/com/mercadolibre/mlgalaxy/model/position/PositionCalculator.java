package com.mercadolibre.mlgalaxy.model.position;

import com.mercadolibre.mlgalaxy.model.Planet;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Position calculator interface, designed to let the door open to
 * many implementations such as 2D.Float or other algorithmic changes.
 *
 * @param <T>
 *            Coordinates type.
 */
public interface PositionCalculator <T extends Point2D>{

    /**
     * Calculates a position by the parameters given.
     *
     * @param day
     *            {@link int} Day of the simulation, used as time of the formula.
     * @param angularVelocity
     *            {@link int} Velocity of the formula.
     * @param sunDistance
     *            {@link int} Distance to the sun (radius).
     *
     * @return A {@link T}.
     */
    T calculatePosition(final int day, final int angularVelocity, final int sunDistance);

    /**
     * Creates a {@link T}.
     *
     * @param x
     *            {@link int} X coordinate.
     * @param y
     *            {@link int} Y coordinate.
     *
     * @return A {@link T}.
     */
    T createCoordinates(double x, double y);

    /**
     * Checks if the planets and the sun are all aligned.
     *
     * @param planets
     *            {@link List} List of planets.
     * @param sunCoordinates
     *            {@link T} Sun coordinates.
     *
     * @return {@link Boolean} Whether all the planets and the sun are aligned.
     */
    boolean sunAligned(final List<Planet> planets, final T sunCoordinates);

    /**
     * Checks if the planets are all aligned.
     *
     * @param planets
     *            {@link List} List of planets.
     *
     * @return {@link Boolean} Whether the planets are aligned.
     */
    boolean aligned(final List<Planet> planets);

    /**
     * Checks if the sun is inside of the figure conformed by the planets on the given.
     *
     * @param sunCoordinates
     *            {@link T} Sun coordinates.
     * @param planets
     *            {@link List} List of planets.
     *
     * @return {@link Boolean} Whether the sun is inside of the figure conformed by the planets given.
     */
    boolean planetTriangleContainsTheSun(final T sunCoordinates, final List<Planet> planets);

}
