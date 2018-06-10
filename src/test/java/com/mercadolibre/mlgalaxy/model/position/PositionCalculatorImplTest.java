package com.mercadolibre.mlgalaxy.model.position;

import com.mercadolibre.mlgalaxy.model.Planet;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionCalculatorImplTest {

    private final PositionCalculator<Point2D.Double> positionCalculator = new PositionCalculatorImpl();

    private final String PLANET_NAME_1 = "Planet 1";
    private final String PLANET_NAME_2 = "Planet 2";
    private final String PLANET_NAME_3 = "Planet 3";

    @Test
    public void coordinatesCenterTest() {
        final Point2D.Double position = positionCalculator.calculatePosition(9, 0, 0);
        assertEquals(0, position.getX(), 0d);
        assertEquals(0, position.getY(), 0d);
    }

    @Test
    public void nonHourlyTest() {
        final Point2D.Double position = positionCalculator.calculatePosition(9, -10, 1000);
        assertEquals((int) (1000 * Math.cos(Math.toRadians(270d))), position.getX(), 0d);
        assertEquals((int) (1000 * Math.sin(Math.toRadians(270d))), position.getY(), 0d);
    }

    @Test
    public void hourlyTest() {
        final Point2D.Double position = positionCalculator.calculatePosition(9, 10, 1000);
        assertEquals((int) (1000 * Math.cos(Math.toRadians(90d))), position.getX(), 0d);
        assertEquals((int) (1000 * Math.sin(Math.toRadians(90d))), position.getY(), 0d);
    }

    @Test
    public void sunIncludedTest() {
        final List<Planet> planets = new ArrayList<>();
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, true, new Point2D.Double(1d, 1d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, true, new Point2D.Double(1d, -1d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, true, new Point2D.Double(-2d, 0d));
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);

        assertTrue(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets));

        p1.setPosition(new Point2D.Double(1d, 2d));
        p2.setPosition(new Point2D.Double(1d, -1d));
        p3.setPosition(new Point2D.Double(-2d, -1d));

        assertTrue(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets));
    }

    @Test
    public void straightSunAndPlanetsTest() {
        final List<Planet> planets = new ArrayList<>();
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, true, new Point2D.Double(0d, 2d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, true, new Point2D.Double(0d, -2d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, true, new Point2D.Double(3d, 0d));
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);

        assertTrue(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d),planets));
    }

    @Test
    public void sunNotIncludedTest() {
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, true, new Point2D.Double(1d, 1d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, true, new Point2D.Double(2d, 3d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, true, new Point2D.Double(3d, 1d));
        final List<Planet> planets = new ArrayList<>();
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);

        assertFalse(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets));

        p1.setPosition(new Point2D.Double(-2d, 3d));
        p2.setPosition(new Point2D.Double(3d, 3d));
        p3.setPosition(new Point2D.Double(2d, -2d));

        assertFalse(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets));
    }

    @Test
    public void planetsAlignedTest() {
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, true, new Point2D.Double(-1d, -1d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, true, new Point2D.Double(1d, 1d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, true, new Point2D.Double(2d, 2d));
        final List<Planet> planets = new ArrayList<>();
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);
        assertTrue(positionCalculator.aligned(planets));
        assertTrue(positionCalculator.sunAligned(planets, new Point2D.Double(0d, 0d)));
    }

    @Test
    public void planetsNotAlignedTest() {
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, true, new Point2D.Double(0d, 2d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, true, new Point2D.Double(3d, 0d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, true, new Point2D.Double(0d, -2d));
        final List<Planet> planets = new ArrayList<>();
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);
        assertFalse(positionCalculator.aligned(planets));
        assertFalse(positionCalculator.sunAligned(planets, new Point2D.Double(0d, 0d)));
    }
}
