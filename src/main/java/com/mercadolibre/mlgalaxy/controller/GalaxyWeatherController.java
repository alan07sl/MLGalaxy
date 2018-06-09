package com.mercadolibre.mlgalaxy.controller;

import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.service.GalaxyWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST API - Galaxy Weather Controller.
 */
@RestController
public class GalaxyWeatherController {

    @Autowired
    private GalaxyWeatherService galaxyWeatherService;

    /**
     * Gets the weather for a specific day.
     *
     * @param day
     *            {@link Integer} The specified day.
     * @return {@link GalaxyWeather}
     */
    @RequestMapping("/clima")
    public final GalaxyWeather weatherByDay(@RequestParam(name="dia") Optional<Integer> day) {
        day.orElseThrow(() -> new IllegalArgumentException("day"));
        return galaxyWeatherService.weatherByDay(day.get()).orElseThrow(() -> new ResourceNotFoundException());
    }
}
