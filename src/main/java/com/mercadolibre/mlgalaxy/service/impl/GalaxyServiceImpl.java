package com.mercadolibre.mlgalaxy.service.impl;

import com.mercadolibre.mlgalaxy.model.Galaxy;
import com.mercadolibre.mlgalaxy.repository.GalaxyRepository;
import com.mercadolibre.mlgalaxy.service.GalaxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to access galaxy data.
 *
 */
@Service
public class GalaxyServiceImpl implements GalaxyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GalaxyServiceImpl.class);


    @Autowired
    private GalaxyRepository galaxyRepository;

    /**
     * Persists the galaxy.
     *
     * @param galaxy
     *            {@link Galaxy} galaxy to be persisted.
     */
    public void save(final Galaxy galaxy) {
        galaxyRepository.save(galaxy);
    }

    /**
     * Cleans galaxy registries.
     */
    public void cleanData() {
        LOGGER.info("Cleaning galaxy data");
        galaxyRepository.deleteAll();
    }
}
