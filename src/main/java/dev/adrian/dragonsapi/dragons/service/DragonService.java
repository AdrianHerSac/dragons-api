package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Category;
import dev.adrian.dragonsapi.dragons.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public interface DragonService extends Service {

    Page<Dragon> findAll(Category category, Pageable pageable);

    Page<Dragon> findAll(Optional<String> name,
                         Optional<Double> maxSpeed,
                         Optional<String> category,
                         org.springframework.data.domain.Pageable pageable);
}