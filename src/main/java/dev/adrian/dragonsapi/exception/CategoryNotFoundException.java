package dev.adrian.dragonsapi.exception;

import java.util.UUID;

/**
 * Excepción lanzada cuando no se encuentra una categoría por su ID.
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(UUID id) {
        super("No se encontró la categoría con ID: " + id);
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
