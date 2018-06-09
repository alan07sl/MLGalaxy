package com.mercadolibre.mlgalaxy.controller;

import com.mercadolibre.mlgalaxy.model.weather.WeatherStatictics;
import com.mercadolibre.mlgalaxy.service.WeatherStaticticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API - Weather statictics Controller.
 */
@RestController
public class WeatherStaticticsController {

    @Autowired
    private WeatherStaticticsService weatherStaticticsService;

    /**
     * Gets the weather statictics.
     *
     * @return {@link WeatherStatictics}
     */
    @RequestMapping("/estadisticas")
    public final WeatherStatictics statictics() {
        return weatherStaticticsService.getStatictics();
    }
}
