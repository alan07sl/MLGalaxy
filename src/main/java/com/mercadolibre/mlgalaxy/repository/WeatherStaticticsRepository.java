package com.mercadolibre.mlgalaxy.repository;

import com.mercadolibre.mlgalaxy.model.statictics.WeatherStatictics;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD Repository used for weather statictics{@link WeatherStatictics}
 *
 */
public interface WeatherStaticticsRepository extends CrudRepository<WeatherStatictics, Integer> {

}
