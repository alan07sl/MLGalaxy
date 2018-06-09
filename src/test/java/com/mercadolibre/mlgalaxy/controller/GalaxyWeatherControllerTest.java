package com.mercadolibre.mlgalaxy.controller;

import com.mercadolibre.mlgalaxy.model.weather.GalaxyWeather;
import com.mercadolibre.mlgalaxy.service.GalaxyWeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GalaxyWeatherControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private final String URL = "http://localhost:";
    private final String REQUIRED_PARAMETER_NOT_PRESENT_ERROR_MESSAGE = "A required parameter is missing";
    private final String RESOURCE_NOT_FOUND = "The resource is not present";

    @Mock
    private GalaxyWeatherService galaxyWeatherService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void weatherByExistingDay() {
        Integer day = ArgumentMatchers.anyInt();
        when(galaxyWeatherService.weatherByDay(day)).thenReturn(Optional.of(new GalaxyWeather()));
        ResponseEntity<String> response = restTemplate.getForEntity(URL + port + "/clima?dia={day}", String.class, day);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void weatherByDayNullParameter() {
        Integer day = ArgumentMatchers.isNull();
        ResponseEntity<String> response = restTemplate.getForEntity(URL + port + "/clima?dia={day}", String.class, day);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), equalTo(REQUIRED_PARAMETER_NOT_PRESENT_ERROR_MESSAGE));
    }

    @Test
    public void weatherByNonExistingDay() {
        Integer day = ArgumentMatchers.anyInt();
        when(galaxyWeatherService.weatherByDay(day)).thenReturn(Optional.empty());
        ResponseEntity<String> response = restTemplate.getForEntity(URL + port + "/clima?dia={day}", String.class, day);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), equalTo(RESOURCE_NOT_FOUND));
    }

}
