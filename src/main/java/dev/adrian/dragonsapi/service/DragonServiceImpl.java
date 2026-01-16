package dev.adrian.dragonsapi.service;

import dev.adrian.dragonsapi.exception.DragonNotFoundException;
import dev.adrian.dragonsapi.model.Dragon;
import dev.adrian.dragonsapi.repository.DragonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DragonServiceImpl implements DragonService {

    private final Logger log = LoggerFactory.getLogger(DragonServiceImpl.class);
    private final DragonRepository dragonRepository;

    public DragonServiceImpl(DragonRepository dragonRepository) {
        this.dragonRepository = dragonRepository;
    }

    @Override
    public Page<Dragon> findAll(Pageable pageable) {
        log.info("Buscando todos los dragones con paginación: page={}, size={}",
                pageable.getPageNumber(), pageable.getPageSize());
        return dragonRepository.findAll(pageable);
    }

    @Override
    public Dragon findById(UUID id) {
        log.info("Buscando dragón por ID: {}", id);
        return dragonRepository.findById(id)
                .orElseThrow(() -> new DragonNotFoundException(id));
    }

    @Override
    public Dragon findByNameIgnoreCase(String name) {
        log.info("Buscando dragón por nombre: {}", name);
        return dragonRepository.findByNameContainingIgnoreCase(name).stream()
                .findFirst()
                .orElseThrow(() -> new DragonNotFoundException("No se encontró dragón con nombre: " + name));
    }

    @Override
    public Dragon save(Dragon dragon) {
        log.info("Guardando nuevo dragón: {}", dragon.getName());
        Dragon savedDragon = dragonRepository.save(dragon);
        log.info("Dragón guardado exitosamente con ID: {}", savedDragon.getId());
        return savedDragon;
    }

    @Override
    public Dragon update(UUID id, Dragon dragon) {
        log.info("Actualizando dragón con ID: {}", id);

        // Verificar que el dragón existe
        Dragon existingDragon = findById(id);

        // Actualizar los campos (el ID se mantiene)
        dragon.setId(existingDragon.getId());
        Dragon updatedDragon = dragonRepository.save(dragon);

        log.info("Dragón actualizado exitosamente: {}", id);
        return updatedDragon;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Eliminando dragón con ID: {}", id);

        // Verificar que el dragón existe antes de eliminarlo
        Dragon dragon = findById(id);

        dragonRepository.delete(dragon);
        log.info("Dragón eliminado exitosamente: {}", id);
    }
}