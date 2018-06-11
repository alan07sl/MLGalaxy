package com.mercadolibre.mlgalaxy.controller;

import com.mercadolibre.mlgalaxy.model.weather.statictics.WeatherStatictics;
import com.mercadolibre.mlgalaxy.service.WeatherStaticticsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API - Weather statictics Controller.
 */
@RestController
@RequestMapping("/estadisticas")
public class WeatherStaticticsController {

    @Autowired
    private WeatherStaticticsService weatherStaticticsService;

    /**
     * Gets the weather statictics.
     *
     * @return {@link WeatherStatictics}
     */
    @GetMapping
    @ApiOperation(value = "Get the weather statictics", notes = "Get the weather statictics.",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final WeatherStatictics statictics() {
        return weatherStaticticsService.getStatictics().orElseThrow(() -> new ResourceNotFoundException());
    }
}
