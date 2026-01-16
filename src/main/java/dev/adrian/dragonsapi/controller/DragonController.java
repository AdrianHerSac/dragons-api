package dev.adrian.dragonsapi.controller;

import dev.adrian.dragonsapi.dto.dragon.DELETEDragonResponseDTO;
import dev.adrian.dragonsapi.dto.dragon.GENERICDragonResponseDTO;
import dev.adrian.dragonsapi.dto.dragon.POSTandPUTDragonRequestDTO;
import dev.adrian.dragonsapi.exception.CategoryNotFoundException;
import dev.adrian.dragonsapi.mapper.DragonMapper;
import dev.adrian.dragonsapi.model.Category;
import dev.adrian.dragonsapi.model.Dragon;
import dev.adrian.dragonsapi.repository.CategoryRepository;
import dev.adrian.dragonsapi.service.DragonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador REST para gestionar operaciones CRUD de dragones.
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/dragons")
@Validated
public class DragonController {

    private static final Logger log = LoggerFactory.getLogger(DragonController.class);

    private final DragonService dragonService;
    private final CategoryRepository categoryRepository;
    private final DragonMapper dragonMapper;

    public DragonController(DragonService dragonService,
            CategoryRepository categoryRepository,
            DragonMapper dragonMapper) {
        this.dragonService = dragonService;
        this.categoryRepository = categoryRepository;
        this.dragonMapper = dragonMapper;
    }

    /**
     * GET /api/dragons
     * Obtiene una lista paginada de todos los dragones.
     *
     * @param pageable Parámetros de paginación (page, size, sort)
     * @return Página de dragones
     */
    @GetMapping
    public ResponseEntity<Page<GENERICDragonResponseDTO>> getAllDragons(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        log.info("GET /api/dragons - Obteniendo lista de dragones");

        Page<Dragon> dragons = dragonService.findAll(pageable);
        Page<GENERICDragonResponseDTO> response = dragons.map(dragonMapper::toResponseDTO);

        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/dragons/{id}
     * Obtiene un dragón específico por su ID.
     *
     * @param id ID del dragón
     * @return Dragón encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<GENERICDragonResponseDTO> getDragonById(@PathVariable UUID id) {
        log.info("GET /api/dragons/{} - Obteniendo dragón por ID", id);

        Dragon dragon = dragonService.findById(id);
        GENERICDragonResponseDTO response = dragonMapper.toResponseDTO(dragon);

        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/dragons/search?name={name}
     * Busca un dragón por su nombre (ignorando mayúsculas).
     *
     * @param name Nombre del dragón a buscar
     * @return Dragón encontrado
     */
    @GetMapping("/search")
    public ResponseEntity<GENERICDragonResponseDTO> searchDragonByName(
            @RequestParam String name) {

        log.info("GET /api/dragons/search?name={} - Buscando dragón por nombre", name);

        Dragon dragon = dragonService.findByNameIgnoreCase(name);
        GENERICDragonResponseDTO response = dragonMapper.toResponseDTO(dragon);

        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/dragons
     * Crea un nuevo dragón.
     *
     * @param requestDTO Datos del dragón a crear
     * @return Dragón creado
     */
    @PostMapping
    public ResponseEntity<GENERICDragonResponseDTO> createDragon(
            @Valid @RequestBody POSTandPUTDragonRequestDTO requestDTO) {

        log.info("POST /api/dragons - Creando nuevo dragón: {}", requestDTO.getName());

        // Buscar la categoría
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(requestDTO.getCategoryId()));

        // Convertir DTO a entidad y guardar
        Dragon dragon = dragonMapper.toEntity(requestDTO, category);
        Dragon savedDragon = dragonService.save(dragon);

        // Convertir a DTO de respuesta
        GENERICDragonResponseDTO response = dragonMapper.toResponseDTO(savedDragon);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT /api/dragons/{id}
     * Actualiza un dragón existente.
     *
     * @param id         ID del dragón a actualizar
     * @param requestDTO Nuevos datos del dragón
     * @return Dragón actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<GENERICDragonResponseDTO> updateDragon(
            @PathVariable UUID id,
            @Valid @RequestBody POSTandPUTDragonRequestDTO requestDTO) {

        log.info("PUT /api/dragons/{} - Actualizando dragón", id);

        // Verificar que el dragón existe
        Dragon existingDragon = dragonService.findById(id);

        // Buscar la categoría
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(requestDTO.getCategoryId()));

        // Actualizar la entidad
        dragonMapper.updateEntity(existingDragon, requestDTO, category);
        Dragon updatedDragon = dragonService.update(id, existingDragon);

        // Convertir a DTO de respuesta
        GENERICDragonResponseDTO response = dragonMapper.toResponseDTO(updatedDragon);

        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/dragons/{id}
     * Elimina un dragón por su ID.
     *
     * @param id ID del dragón a eliminar
     * @return Mensaje de confirmación con los datos del dragón eliminado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DELETEDragonResponseDTO> deleteDragon(@PathVariable UUID id) {
        log.info("DELETE /api/dragons/{} - Eliminando dragón", id);

        // Obtener el dragón antes de eliminarlo para incluirlo en la respuesta
        Dragon dragon = dragonService.findById(id);

        // Eliminar
        dragonService.deleteById(id);

        // Crear respuesta
        DELETEDragonResponseDTO response = dragonMapper.toDeleteResponseDTO(
                dragon,
                "Dragón eliminado exitosamente");

        return ResponseEntity.ok(response);
    }
}
