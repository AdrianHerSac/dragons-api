package dev.adrian.dragonsapi.dto.dragon;

import dev.adrian.dragonsapi.model.Dragon;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO para crear o actualizar un dragón.
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class POSTandPUTDragonRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotBlank(message = "El color no puede estar vacío")
    private String color;

    @NotBlank(message = "La imagen no puede estar vacía")
    private String image;

    @NotNull(message = "El tamaño no puede ser nulo")
    private Dragon.Size size;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser positivo")
    private Double price;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "La categoría no puede ser nula")
    private UUID categoryId;
}