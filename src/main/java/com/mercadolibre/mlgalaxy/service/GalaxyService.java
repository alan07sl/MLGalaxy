package com.mercadolibre.mlgalaxy.service;

import com.mercadolibre.mlgalaxy.model.Galaxy;

public interface GalaxyService {

    void save(Galaxy galaxy);
    void cleanData();
}
