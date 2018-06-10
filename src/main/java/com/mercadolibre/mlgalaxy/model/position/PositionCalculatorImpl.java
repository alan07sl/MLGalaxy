package com.mercadolibre.mlgalaxy.model.position;

import com.mercadolibre.mlgalaxy.model.Planet;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Position Calculator impl with Double coordinates in squared R. {@link PositionCalculator}.
 */
public class PositionCalculatorImpl implements PositionCalculator<Point2D.Double> {

    private final double ZERO = 0.0;

    @Override
    public Point2D.Double calculatePosition(final int day, final int angularVelocity, final int sunDistance) {
        int distance = (day * angularVelocity);
        if (distance < 0) {
            distance = 360 - Math.abs(distance % 360);
        }
        final int angle = distance % 360;

        return createCoordinates((int) (sunDistance * Math.cos(Math.toRadians(angle))),
                (int) (sunDistance * Math.sin(Math.toRadians(angle))));
    }

    @Override
    public Point2D.Double createCoordinates(final double x, final double y) {
        return new Point2D.Double(x, y);
    }

    @Override
    public boolean sunAligned(final List<Planet> planets, final Point2D.Double sunCoordinates) {
        if (!aligned(planets)) {
            return false;
        }

        return isStraight(planets.get(0).getPosition(), planets.get(1).getPosition(), sunCoordinates);
    }

    @Override
    public boolean aligned(final List<Planet> planets) {
        boolean isStraight = true;
        for (int i = 0; i < planets.size() - 2; i++) {
            isStraight &= isStraight(planets.get(i).getPosition(), planets.get(i + 1).getPosition(), planets.get(i + 2).getPosition());
            if (isStraight) {
                break;
            }
        }
        return isStraight;
    }

    /**
     * Checks if the 3 coordinates {@link Point2D} are aligned in squared(R).
     * Validated by the following equation: (y2 - y1) * (x3 - x2) = (y3 - y2) * (x2 - x1).
     *
     * @param point1
     *            {@link Point2D} First coordinate.
     * @param point2
     *            {@link Point2D} Second coordinate.
     * @param point3
     *            {@link Point2D} Third coordinate.
     * @return {@link Boolean} Whether the 3 coordinates {@link Point2D} are aligned in squared(R).
     */
    private boolean isStraight(final Point2D point1, final Point2D point2,
                               final Point2D point3) {
        return ((point2.getY() - point1.getY()) * (point3.getX() - point2.getX())) == ((point3.getY() - point2.getY())
                * (point2.getX() - point1.getX()));
    }

    /**
     * Signs the area between 3 points
     *
     * @param p1
     *            {@link Point2D} First coordinate.
     * @param p2
     *            {@link Point2D} Second coordinate.
     * @param p3
     *            {@link Point2D} Third coordinate.
     *
     * @return {@link Double} The signed area of the triangle.
     */
    private Double sign (Point2D p1, Point2D p2, Point2D p3)
    {
        return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
    }

    /**
     * Checks whether the sun is inside a triangle conformed by the planets on the list given.
     *
     * @param sunPosition
     *            {@link Point2D} Second coordinate.
     * @param planets
     *            {@link List} Planets to get its coordinates.
     *
     * @return {@link Boolean} Whether the sun {@link Point2D.Double} is inside a triangle conformed by the planets {@link List} on the list given.
     */
    @Override
    public boolean planetTriangleContainsTheSun(Point2D.Double sunPosition, List<Planet> planets) {
        if(planets.size() < 3) return false;
        return pointInTriangle(sunPosition, planets.get(0).getPosition(),planets.get(1).getPosition(), planets.get(2).getPosition());
    }

    /**
     * Checks if the point is inside the triangle with the vertex on the 3 coordinates given.
     *
     * @param pt
     *            {@link Point2D} point to check if it is inside the triangle.
     * @param v1
     *            {@link Point2D} First vertex.
     * @param v2
     *            {@link Point2D} Second vertex.
     * @param v3
     *            {@link Point2D} Third vertex.
     *
     * @return {@link Boolean} Whether the point is inside the triangle with the vertex on the 3 coordinates {@link Point2D} given.
     */
    private boolean pointInTriangle (Point2D pt, Point2D v1, Point2D v2, Point2D v3)
    {
        boolean b1, b2, b3;

        b1 = sign(pt, v1, v2) < ZERO;
        b2 = sign(pt, v2, v3) < ZERO;
        b3 = sign(pt, v3, v1) < ZERO;

        return ((b1 == b2) && (b2 == b3));
    }
}
