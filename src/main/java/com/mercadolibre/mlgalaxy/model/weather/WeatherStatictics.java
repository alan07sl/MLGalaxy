package com.mercadolibre.mlgalaxy.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    private Integer id;
    private Integer rainMaxDay;
    private List<WeatherQuantity> QuantityWeather;

    @JsonIgnore
    private List<GalaxyWeather> weathers;

    /**
     * Constructor.
     */
    protected WeatherStatictics() {
        weathers = new ArrayList<>();
        QuantityWeather = new ArrayList<>();
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
        QuantityWeather = weathers.stream()
                        .collect(Collectors.groupingBy(weather -> weather.getWeather(), Collectors.counting())) //
                        .entrySet().stream()
                        .map(entry -> new WeatherQuantity(entry.getKey(), entry.getValue().intValue())) //
                        .collect(Collectors.toList());
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue
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
    @Column(name = "RAIN_MAX_DAY", nullable = false)
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
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_STATICTICS_ID")
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
     * @return QuantityWeather
     */
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_STATICTICS_ID")
    public List<WeatherQuantity> getQuantityWeather() {
        return QuantityWeather;
    }

    /**
     * @param quantityWeather
     *          QuantityWeather to set
     */
    public void setQuantityWeather(final List<WeatherQuantity> quantityWeather) {
        this.QuantityWeather = quantityWeather;
    }
}
