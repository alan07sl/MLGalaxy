package com.mercadolibre.mlgalaxy.model;

import javax.persistence.*;
import java.awt.geom.Point2D;

/**
 * Planet model class
 */
@Entity
@Table(name = "PLANET")
public class Planet {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "ANGULAR_VELOCITY", nullable = false)
    private Integer angularVelocity;
    @Column(name = "SUN_DISTANCE", nullable = false)
    private Integer distanceToSun;
    @Column(name = "POSITION", nullable = false)
    private Point2D position;

    /**
     * Constructor.
     */
    public Planet() {

    }

    /**
     * Constructor.
     *
     * @param name
     *            {@link String} The Planet name.
     * @param angularVelocity
     *            {@link Short} Planet's angular velocity.
     * @param distanceToSun
     *            {@link Integer} Distance between the planet and the sun.
     * @param hourly
     *            {@link Boolean} Planet moving hourly or not.
     * @param initialPosition
     *            {@link Point2D} Planet's initial position.
     */
    public Planet(final String name, final int angularVelocity, final int distanceToSun, final boolean hourly,
                   final Point2D initialPosition) {
        this.name = name;
        this.distanceToSun = distanceToSun;
        this.angularVelocity = ((hourly ? -1 : 1) * angularVelocity);
        position = initialPosition;
    }

    /**
     * @return id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * @param id
     *            id to be set
     */
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return Name of the planet
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            Name to be set
     */
    protected final void setName(final String name) {
        this.name = name;
    }

    /**
     * @return angularVelocity of the planet
     */
    public final Integer getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * @param angularVelocity
     *            angularVelocity to be set
     */
    protected final void setAngularVelocity(final Integer angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    /**
     * @return position of the planet
     */
    public final Point2D getPosition() {
        return position;
    }

    /**
     * @param position
     *            the position to be set
     */
    public final void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * @return the distanceToSun
     */
    public final int getDistanceToSun() {
        return distanceToSun;
    }

    /**
     * @param distanceToSun
     *            the distanceToSun to be set
     */
    protected final void setDistanceToSun(final int distanceToSun) {
        this.distanceToSun = distanceToSun;
    }

    /**
     * Calculates distance between two planets.
     *
     * @param planet
     *            {@link Planet} Planet to be used as parameter to the relative distance.
     * @return {@link Double} The resultant distance.
     */
    public double distance(final Planet planet) {
        return getPosition().distance(planet.getPosition());
    }
}
