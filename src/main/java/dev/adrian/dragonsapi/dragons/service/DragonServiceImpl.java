package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Dragon;
import dev.adrian.dragonsapi.dragons.repository.DragonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DragonServiceImpl implements DragonService {

    private final Logger log = LoggerFactory.getLogger(DragonServiceImpl.class);
    private final DragonRepository dragonRepository;

    public DragonServiceImpl(DragonRepository dragonRepository) {
        this.dragonRepository = dragonRepository;
    }

    @Override
    public Page<Dragon> findAll(Optional<String> name,
                                Optional<Double> maxSpeed,
                                Optional<String> category,
                                Pageable pageable) {

        log.info("In DragonServiceImpl findAll with filters");

        return dragonRepository.findAll(pageable);
    }
}