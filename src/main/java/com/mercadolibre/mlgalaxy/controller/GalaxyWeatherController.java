package com.mercadolibre.mlgalaxy.controller;

import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.service.GalaxyWeatherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST API - Galaxy Weather Controller.
 */
@RestController
@RequestMapping("/clima")
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
    @GetMapping
    @ApiOperation(value = "Get the weather for a particular day", notes = "Get the weather for a particular day. e.g. GET → http://….../clima?dia=566 → Respuesta: {“dia”:566, “clima”:”lluvia”}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final GalaxyWeather weatherByDay(@RequestParam(name="dia") Optional<Integer> day) {
        day.orElseThrow(() -> new IllegalArgumentException("dia"));
        return galaxyWeatherService.weatherByDay(day.get()).orElseThrow(() -> new ResourceNotFoundException());
    }
}
