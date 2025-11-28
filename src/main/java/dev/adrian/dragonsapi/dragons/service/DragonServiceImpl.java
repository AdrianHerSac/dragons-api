package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Dragon;
import dev.adrian.dragonsapi.dragons.repository.DragonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DragonServiceImpl implements DragonService {

    private final Logger log = LoggerFactory.getLogger(DragonServiceImpl.class);
    private final DragonRepository dragonRepository;

    public DragonServiceImpl(DragonRepository dragonRepository) {
        this.dragonRepository = dragonRepository;
    }

    @Override
    public Page<Dragon> findAll(Pageable pageable) {

        log.info("Buscando todos los datos para obtener los dragones");

        return dragonRepository.findAll(pageable);
    }

    @Override
    public Dragon findById(long id) {
        log.info("");
        return null;
    }

    @Override
    public Dragon findByNameIgnoreCase(String name) {
        return null;
    }

    @Override
    public Dragon save(Dragon dragon) {
        return null;
    }

    @Override
    public void delete(Dragon dragon) {
    }
}