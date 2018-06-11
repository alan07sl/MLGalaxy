package com.mercadolibre.mlgalaxy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mlgalaxy.model.weather.statictics.WeatherStatictics;
import com.mercadolibre.mlgalaxy.service.WeatherStaticticsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherStaticticsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    @InjectMocks
    private WeatherStaticticsController weatherStaticticsController;

    @LocalServerPort
    private int port;

    private final String URL = "http://localhost:";
    private final String RESOURCE_NOT_FOUND = "The resource is not present";

    @Mock
    private WeatherStaticticsService weatherStaticticsService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private ResponseEntity<String> getStringResponseEntity() {
        return restTemplate.getForEntity(URL + port + "/estadisticas", String.class);
    }

    @Test
    public void existingStaticticsTest() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        WeatherStatictics aWeatherStatictics = new WeatherStatictics(10,new ArrayList<>());

        when(weatherStaticticsService.getStatictics()).thenReturn(Optional.of(aWeatherStatictics));
        ResponseEntity<String> response = getStringResponseEntity();

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(mapper.writeValueAsString(aWeatherStatictics)));
    }

    @Test
    public void nonExistingStaticticsTest() {
        when(weatherStaticticsService.getStatictics()).thenReturn(Optional.empty());
        ResponseEntity<String> response = getStringResponseEntity();

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), equalTo(RESOURCE_NOT_FOUND));
    }

}
