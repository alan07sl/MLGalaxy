package com.mercadolibre.mlgalaxy.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Model class of the weather on the galaxy by type and day.
 */
@Entity
@Table(name = "WEATHER", indexes = { @Index(name = "DAY_INDEX", columnList = "day") })
public class GalaxyWeather {

    @JsonIgnore
    private Integer id;
    private GalaxyWeatherType weather;
    private Integer day;

    /**
     * Constructor.
     */
    public GalaxyWeather() {

    }

    /**
     * Constructor.
     *
     * @param weatherType
     *            {@link GalaxyWeatherType} Weather type.
     * @param day
     *            {@link Integer} the specific day to get the weather that occurred.
     */
    public GalaxyWeather(final GalaxyWeatherType weatherType, final Integer day) {
        weather = weatherType;
        this.day = day;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue
    public final Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the weather type
     */
    @Enumerated
    public final GalaxyWeatherType getWeather() {
        return weather;
    }

    /**
     * @param weatherType
     *            the weatherType to set
     */
    public final void setWeather(final GalaxyWeatherType weatherType) {
        weather = weatherType;
    }

    /**
     * @return the day
     */
    @Column(name = "day", nullable = false)
    public final Integer getDay() {
        return day;
    }

    /**
     * @param day
     *            the day to set
     */
    public final void setDay(final Integer day) {
        this.day = day;
    }
}
