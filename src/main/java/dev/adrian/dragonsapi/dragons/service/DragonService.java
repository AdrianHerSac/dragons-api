package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DragonService {

    Page<Dragon> findAll(Pageable pageable);

    Dragon findById(long id);
    Dragon findByNameIgnoreCase(String name);

    Dragon save(Dragon dragon);
    void delete(Dragon dragon);
}