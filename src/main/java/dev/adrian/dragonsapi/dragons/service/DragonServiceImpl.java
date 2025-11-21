package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Category;
import dev.adrian.dragonsapi.dragons.model.Dragon;
import dev.adrian.dragonsapi.dragons.repository.DragonRepository;
import jakarta.persistence.criteria.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 */
@Service
@CacheConfig(cacheNames = {"dragons"})
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

        Specification<Dragon> specName = (root, query, criteriaBuilder) ->
                name.map(n -> criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + n.toLowerCase() + "%"))
                        .orElse(criteriaBuilder.conjunction());

        Specification<Dragon> specMaxSpeed = (root, query, criteriaBuilder) ->
                maxSpeed.map(s -> criteriaBuilder.lessThanOrEqualTo(root.get("speed"), s))
                        .orElse(criteriaBuilder.conjunction());

        Specification<Dragon> specCategory = (root, query, criteriaBuilder) ->
                category.map(c -> {
                    Join<Dragon, Category> categoryJoin = root.join("category");
                    return criteriaBuilder.like(
                            criteriaBuilder.lower(categoryJoin.get("name")),
                            "%" + c.toLowerCase() + "%");
                }).orElse(criteriaBuilder.conjunction());

        Specification<Dragon> finalSpec = Specification.where(specName)
                .and(specMaxSpeed)
                .and(specCategory);

        return dragonRepository.findAll(finalSpec, pageable);
    }
}