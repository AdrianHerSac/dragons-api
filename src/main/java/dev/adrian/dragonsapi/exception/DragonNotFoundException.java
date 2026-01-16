package dev.adrian.dragonsapi.exception;

import java.util.UUID;

/**
 * Excepción lanzada cuando no se encuentra un dragón por su ID.
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
public class DragonNotFoundException extends RuntimeException {

    public DragonNotFoundException(UUID id) {
        super("No se encontró el dragón con ID: " + id);
    }

    public DragonNotFoundException(String message) {
        super(message);
    }
}
