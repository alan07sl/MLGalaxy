package com.mercadolibre.mlgalaxy.repository;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD Repository used for the galaxy. {@link Galaxy}
 */
public interface GalaxyRepository extends CrudRepository<Galaxy, Integer>{
}
