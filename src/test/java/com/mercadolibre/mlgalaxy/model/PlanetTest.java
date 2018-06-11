package com.mercadolibre.mlgalaxy.model;

import org.junit.Test;

import java.awt.geom.Point2D;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class PlanetTest {

    @Test
    public void hourlyPlanetTest() {
        final Planet p = new Planet("Hourly Planet",  10, 10, Boolean.TRUE, new Point2D.Double(0d, 0d));
        assertThat(p.getAngularVelocity(), lessThan(0));
    }

    @Test
    public void nonHourlyPlanetTest() {
        final Planet p = new Planet("Hourly Planet",  10, 10, Boolean.FALSE, new Point2D.Double(0d, 0d));
        assertThat(p.getAngularVelocity(), greaterThan(0));
    }

    @Test
    public void planetCollidingTest() {
        final Planet p1 = new Planet("Planet 1",  10, 10, Boolean.TRUE, new Point2D.Double(0d, 0d));
        final Planet p2 = new Planet("Planet 2",  10, 10, Boolean.TRUE, new Point2D.Double(0d, 0d));

        assertThat(0.0, equalTo(p1.distance(p2)));
    }

    @Test
    public void planetNonCollidingTest() {
        final Planet p1 = new Planet("Planet 1",  10, 10, Boolean.TRUE, new Point2D.Double(0d, 1d));
        final Planet p2 = new Planet("Planet 2",  10, 10, Boolean.TRUE, new Point2D.Double(0d, 5d));

        final Double planetsDistance = p1.distance(p2);

        assertNotEquals(0, planetsDistance);
        assertThat(planetsDistance, equalTo(4.0));
    }

}
