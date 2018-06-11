package com.mercadolibre.mlgalaxy.model.weather.statictics;

import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeatherType;
import com.mercadolibre.mlgalaxy.model.weather.WeatherQuantity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class WeatherStaticticsTest {

    @Test
    public void testEstadisticas() {
        final List<GalaxyWeather> weathers = new ArrayList<>();
        weathers.add(new GalaxyWeather(GalaxyWeatherType.RAIN, 1));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.RAIN, 2));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.RAIN, 3));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.RAIN, 4));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.DROUGHT, 5));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.DROUGHT, 6));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.DROUGHT, 7));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.DROUGHT, 8));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.RAIN, 9));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.OPTIMAL, 10));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.REGULAR, 11));
        weathers.add(new GalaxyWeather(GalaxyWeatherType.REGULAR, 12));
        final WeatherStatictics statictics = new WeatherStatictics(10, weathers);
        assertWeatherQuantity(statictics.getQuantityWeather(), GalaxyWeatherType.RAIN, 5);
        assertWeatherQuantity(statictics.getQuantityWeather(), GalaxyWeatherType.OPTIMAL, 1);
        assertWeatherQuantity(statictics.getQuantityWeather(), GalaxyWeatherType.DROUGHT, 4);
        assertWeatherQuantity(statictics.getQuantityWeather(), GalaxyWeatherType.REGULAR, 2);
    }

    private static void assertWeatherQuantity(final List<WeatherQuantity> weatherQuantites, final GalaxyWeatherType weatherType,
                                              final Integer quantityToBeTested) {
        WeatherQuantity weatherQuantity = null;
        for (final WeatherQuantity wq : weatherQuantites) {
            if (wq.getWeather() == weatherType) {
                weatherQuantity = wq;
                break;
            }
        }
        assertThat(weatherQuantity, notNullValue());
        assertThat(quantityToBeTested, equalTo(weatherQuantity.getQuantity()));
    }
}
