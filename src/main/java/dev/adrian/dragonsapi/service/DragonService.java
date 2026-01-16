package dev.adrian.dragonsapi.service;

import dev.adrian.dragonsapi.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DragonService {

    Page<Dragon> findAll(Pageable pageable);

    Dragon findById(UUID id);

    Dragon findByNameIgnoreCase(String name);

    Dragon save(Dragon dragon);

    Dragon update(UUID id, Dragon dragon);

    void deleteById(UUID id);
}