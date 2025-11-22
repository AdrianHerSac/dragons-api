package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DragonService {

    Page<Dragon> findAll(Optional<String> name,
                         Optional<Double> maxSpeed,
                         Optional<String> category,
                         Pageable pageable);

}