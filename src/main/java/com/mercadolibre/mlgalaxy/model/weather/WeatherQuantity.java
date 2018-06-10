package com.mercadolibre.mlgalaxy.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Model of that references type of weather and quantity of occurrences.
 */
@Entity
@Table(name = "WEATHER_QUANTITY")
public class WeatherQuantity {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
    @Enumerated
    private GalaxyWeatherType weather;
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    /**
     * Constructor.
     */
    public WeatherQuantity() {

    }

    /**
     * Constructor.
     *
     * @param weather
     *            {@link GalaxyWeatherType} Weather type.
     * @param quantity
     *            {@link Integer} Quantity.
     */
    public WeatherQuantity(final GalaxyWeatherType weather, final Integer quantity) {
        this.weather = weather;
        this.quantity = quantity;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the weather
     */
    public GalaxyWeatherType getWeather() {
        return weather;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param weather
     *            the weather to set
     */
    public void setWeather(final GalaxyWeatherType weather) {
        this.weather = weather;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
