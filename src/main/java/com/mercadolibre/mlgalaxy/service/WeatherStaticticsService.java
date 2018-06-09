package com.mercadolibre.mlgalaxy.service;

import com.mercadolibre.mlgalaxy.model.weather.WeatherStatictics;

import java.util.Optional;

public interface WeatherStaticticsService {

    Optional<WeatherStatictics> getStatictics();

    void cleanData();
}
