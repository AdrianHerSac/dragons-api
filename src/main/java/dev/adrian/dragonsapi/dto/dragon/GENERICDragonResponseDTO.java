package dev.adrian.dragonsapi.dto.dragon;

import dev.adrian.dragonsapi.dto.category.CategoryDTO;
import dev.adrian.dragonsapi.model.Dragon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO genérico para representar un dragón en las respuestas de la API.
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GENERICDragonResponseDTO {

    private UUID id;
    private String name;
    private String color;
    private Dragon.Size size;
    private Double price;
    private Integer stock;
    private String image;
    private CategoryDTO category;
}