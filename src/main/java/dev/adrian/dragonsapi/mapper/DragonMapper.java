package dev.adrian.dragonsapi.mapper;

import dev.adrian.dragonsapi.dto.category.CategoryDTO;
import dev.adrian.dragonsapi.dto.dragon.DELETEDragonResponseDTO;
import dev.adrian.dragonsapi.dto.dragon.GENERICDragonResponseDTO;
import dev.adrian.dragonsapi.dto.dragon.POSTandPUTDragonRequestDTO;
import dev.adrian.dragonsapi.model.Category;
import dev.adrian.dragonsapi.model.Dragon;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades Dragon y sus DTOs.
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
@Component
public class DragonMapper {

    /**
     * Convierte un DTO de request a una entidad Dragon.
     *
     * @param dto      DTO con los datos del dragón
     * @param category Categoría asociada al dragón
     * @return Entidad Dragon
     */
    public Dragon toEntity(POSTandPUTDragonRequestDTO dto, Category category) {
        Dragon dragon = new Dragon();
        dragon.setName(dto.getName());
        dragon.setColor(dto.getColor());
        dragon.setImage(dto.getImage());
        dragon.setSize(dto.getSize());
        dragon.setPrice(dto.getPrice());
        dragon.setStock(dto.getStock());
        dragon.setCategory(category);
        return dragon;
    }

    /**
     * Actualiza una entidad Dragon existente con datos del DTO.
     *
     * @param dragon   Entidad existente a actualizar
     * @param dto      DTO con los nuevos datos
     * @param category Nueva categoría (puede ser la misma)
     */
    public void updateEntity(Dragon dragon, POSTandPUTDragonRequestDTO dto, Category category) {
        dragon.setName(dto.getName());
        dragon.setColor(dto.getColor());
        dragon.setImage(dto.getImage());
        dragon.setSize(dto.getSize());
        dragon.setPrice(dto.getPrice());
        dragon.setStock(dto.getStock());
        dragon.setCategory(category);
    }

    /**
     * Convierte una entidad Dragon a un DTO de respuesta.
     *
     * @param dragon Entidad Dragon
     * @return DTO de respuesta
     */
    public GENERICDragonResponseDTO toResponseDTO(Dragon dragon) {
        return GENERICDragonResponseDTO.builder()
                .id(dragon.getId())
                .name(dragon.getName())
                .color(dragon.getColor())
                .image(dragon.getImage())
                .size(dragon.getSize())
                .price(dragon.getPrice())
                .stock(dragon.getStock())
                .category(toCategoryDTO(dragon.getCategory()))
                .build();
    }

    /**
     * Convierte una entidad Category a CategoryDTO.
     *
     * @param category Entidad Category
     * @return CategoryDTO
     */
    private CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    /**
     * Crea un DTO de respuesta para operaciones de eliminación.
     *
     * @param dragon  Dragón eliminado
     * @param message Mensaje descriptivo
     * @return DTO de respuesta de eliminación
     */
    public DELETEDragonResponseDTO toDeleteResponseDTO(Dragon dragon, String message) {
        return DELETEDragonResponseDTO.builder()
                .message(message)
                .deletedDragon(toResponseDTO(dragon))
                .build();
    }
}
