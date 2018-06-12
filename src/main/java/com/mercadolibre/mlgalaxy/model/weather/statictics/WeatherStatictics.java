package com.mercadolibre.mlgalaxy.model.weather.statictics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.model.weather.WeatherQuantity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Statictics of the galaxy weather.
 */
@Entity
@Table(name = "STATICTICS")
public class WeatherStatictics {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
    @Column(name = "RAIN_MAX_DAY", nullable = false)
    private Integer rainMaxDay;
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_STATICTICS_ID")
    private List<WeatherQuantity> quantityWeather;
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_STATICTICS_ID")
    @JsonIgnore
    private List<GalaxyWeather> weathers;

    /**
     * Constructor.
     */
    protected WeatherStatictics() {
        weathers = new ArrayList<>();
        quantityWeather = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param rainMaxDay
     *            {@link Integer} Day with max rain.
     * @param weathers
     *            {@link List} Weather type and quantity list.
     */
    public WeatherStatictics(final Integer rainMaxDay, final List<GalaxyWeather> weathers) {
        this.rainMaxDay = rainMaxDay;
        this.weathers = weathers;
        quantityWeather = weathers.stream()
                        .collect(Collectors.groupingBy(weather -> weather.getWeather(), Collectors.counting()))
                        .entrySet().stream()
                        .map(entry -> new WeatherQuantity(entry.getKey(), entry.getValue().intValue()))
                        .collect(Collectors.toList());
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
     * @return the rainMaxDay
     */
    public Integer getRainMaxDay() {
        return rainMaxDay;
    }

    /**
     * @param rainMaxDay
     *            the rainMaxDay to set
     */
    public void setRainMaxDay(final Integer rainMaxDay) {
        this.rainMaxDay = rainMaxDay;
    }

    /**
     * @return Weathers
     */
    public List<GalaxyWeather> getWeathers() {
        return weathers;
    }

    /**
     * @param weathers
     *            Weathers to set
     */
    public void setWeathers(final List<GalaxyWeather> weathers) {
        this.weathers = weathers;
    }

    /**
     * @return quantityWeather
     */
    public List<WeatherQuantity> getQuantityWeather() {
        return quantityWeather;
    }

    /**
     * @param quantityWeather
     *          quantityWeather to set
     */
    public void setQuantityWeather(final List<WeatherQuantity> quantityWeather) {
        this.quantityWeather = quantityWeather;
    }
}
