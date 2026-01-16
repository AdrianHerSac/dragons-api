package dev.adrian.dragonsapi.repository;

import dev.adrian.dragonsapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio para gestionar operaciones de persistencia de Category.
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByNombre(String nombre);
}
