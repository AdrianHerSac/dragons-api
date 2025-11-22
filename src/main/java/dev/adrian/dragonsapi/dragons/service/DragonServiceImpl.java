package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Dragon;
import dev.adrian.dragonsapi.dragons.repository.DragonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio del modelo Dragon
 *
 *
 */
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

        log.info("Buscando por filtros");

        Specification<Dragon> spec = Specification.where((Specification<Dragon>) null);

        if (name.isPresent()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.get().toLowerCase() + "%")
            );
        }

        if (maxSpeed.isPresent()) {
            spec = spec.and((root, query, cb) ->
                    cb.le(root.get("category").get("speed"), maxSpeed.get())
            );
        }

        if (category.isPresent()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("category").get("name")),
                            "%" + category.get().toLowerCase() + "%")
            );
        }

        return dragonRepository.findAll(spec, pageable);
    }
}