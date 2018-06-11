package com.mercadolibre.mlgalaxy.model;

import com.mercadolibre.mlgalaxy.model.position.PositionCalculator;
import com.mercadolibre.mlgalaxy.model.position.impl.PositionCalculatorImpl;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Galaxy model, by default the sun position is on the origin.
 * It accumulates the weathers happening on the simulation.
 */
@Entity
@Table(name = "GALAXY")
public class Galaxy {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_GALAXIA_ID")
    private List<Planet> planets;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "X_SOL")),
            @AttributeOverride(name = "y", column = @Column(name = "Y_SOL")),
    })
    @Target(Point2D.Double.class)
    private Point2D sunCoordinates;
    @Transient
    private final PositionCalculator<?> positionCalculator;

    /**
     * Constructor. Uses {@link PositionCalculatorImpl} by default.
     */
    public Galaxy() {
        positionCalculator = new PositionCalculatorImpl();
        planets = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param positionCalculator
     *            {@link PositionCalculator} The position calculator to set the sun coordinates.
     */
    public Galaxy(final PositionCalculator<?> positionCalculator) {
        planets = new ArrayList<>();
        sunCoordinates = positionCalculator.createCoordinates(0, 0);
        this.positionCalculator = positionCalculator;
    }

    /**
     * @return the id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to be set
     */
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the planets
     */
    public final List<Planet> getPlanets() {
        return planets;
    }

    /**
     * @param planets
     *            the planets to be set
     */
    protected final void setPlanets(final List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * @return the sunCoordinates
     */
    public final Point2D getSunCoordinates() {
        return sunCoordinates;
    }

    /**
     * @param sunCoordinates
     *            the sunCoordinates to be set
     */
    protected final void setSunCoordinates(final Point2D sunCoordinates) {
        this.sunCoordinates = sunCoordinates;
    }

    /**
     * Adds a planet to the galaxy.
     *
     * @param name
     *            {@link String} Name of the planet.
     * @param angularVelocity
     *            {@link Integer} The angular velocity of the planet.
     * @param sunDistance
     *            {@link Integer} The distance from this planet to the sun.
     * @param hourly
     *            {@link Boolean} Whether the planet moves hourly or not.
     * @param x
     *            {@link Integer} X coordinate.
     * @param y
     *            {@link Integer} Y coordinate.
     */
    public void addPlanet(final String name, final int angularVelocity, final int sunDistance, final boolean hourly,
                          final int x, final int y) {
        planets.add(new Planet(name, angularVelocity, sunDistance, hourly, positionCalculator.createCoordinates(x, y)));
    }
}
