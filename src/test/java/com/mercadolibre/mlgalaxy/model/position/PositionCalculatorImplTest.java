package com.mercadolibre.mlgalaxy.model.position;

import com.mercadolibre.mlgalaxy.model.Planet;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

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
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, Boolean.TRUE, new Point2D.Double(1d, 1d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, Boolean.TRUE, new Point2D.Double(1d, -1d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, Boolean.TRUE, new Point2D.Double(-2d, 0d));
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);

        assertThat(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets), is(Boolean.TRUE));

        p1.setPosition(new Point2D.Double(1d, 2d));
        p2.setPosition(new Point2D.Double(1d, -1d));
        p3.setPosition(new Point2D.Double(-2d, -1d));

        assertThat(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets), is(Boolean.TRUE));
    }

    @Test
    public void straightSunAndPlanetsTest() {
        final List<Planet> planets = new ArrayList<>();
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, Boolean.TRUE, new Point2D.Double(0d, 2d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, Boolean.TRUE, new Point2D.Double(0d, -2d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, Boolean.TRUE, new Point2D.Double(3d, 0d));
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);

        assertThat(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d),planets), is(Boolean.TRUE));
    }

    @Test
    public void sunNotIncludedTest() {
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, Boolean.TRUE, new Point2D.Double(1d, 1d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, Boolean.TRUE, new Point2D.Double(2d, 3d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, Boolean.TRUE, new Point2D.Double(3d, 1d));
        final List<Planet> planets = new ArrayList<>();
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);

        assertThat(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets), is(Boolean.FALSE));

        p1.setPosition(new Point2D.Double(-2d, 3d));
        p2.setPosition(new Point2D.Double(3d, 3d));
        p3.setPosition(new Point2D.Double(2d, -2d));

        assertThat(positionCalculator.planetTriangleContainsTheSun(new Point2D.Double(0d, 0d), planets), is(Boolean.FALSE));
    }

    @Test
    public void planetsAlignedTest() {
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, Boolean.TRUE, new Point2D.Double(-1d, -1d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, Boolean.TRUE, new Point2D.Double(1d, 1d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, Boolean.TRUE, new Point2D.Double(2d, 2d));
        final List<Planet> planets = new ArrayList<>();
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);
        assertThat(positionCalculator.aligned(planets), is(Boolean.TRUE));
        assertThat(positionCalculator.sunAligned(planets, new Point2D.Double(0d, 0d)), is(Boolean.TRUE));
    }

    @Test
    public void planetsNotAlignedTest() {
        final Planet p1 = new Planet(PLANET_NAME_1,  1, 1, Boolean.TRUE, new Point2D.Double(0d, 2d));
        final Planet p2 = new Planet(PLANET_NAME_2,  1, 1, Boolean.TRUE, new Point2D.Double(3d, 0d));
        final Planet p3 = new Planet(PLANET_NAME_3,  1, 1, Boolean.TRUE, new Point2D.Double(0d, -2d));
        final List<Planet> planets = new ArrayList<>();
        planets.add(p1);
        planets.add(p2);
        planets.add(p3);
        assertThat(positionCalculator.aligned(planets), is(Boolean.FALSE));
        assertThat(positionCalculator.sunAligned(planets, new Point2D.Double(0d, 0d)), is(Boolean.FALSE));
    }
}
