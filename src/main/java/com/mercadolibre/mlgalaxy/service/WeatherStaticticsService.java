package com.mercadolibre.mlgalaxy.service;

import com.mercadolibre.mlgalaxy.model.statictics.WeatherStatictics;

import java.util.Optional;

public interface WeatherStaticticsService {

    Optional<WeatherStatictics> getStatictics();
    void save(WeatherStatictics weatherStatictics);
    void cleanData();
}
