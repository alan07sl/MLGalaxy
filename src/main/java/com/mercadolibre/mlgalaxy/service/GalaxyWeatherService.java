package com.mercadolibre.mlgalaxy.service;

import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;

import java.util.Optional;

public interface GalaxyWeatherService {
    Optional<GalaxyWeather> weatherByDay(final Integer day);
}
