package dev.adrian.dragonsapi.dragons.repository;

import dev.adrian.dragonsapi.dragons.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository {

    Page<Category> findAll(Pageable pageable);
    
    Optional<Category> findCategoryById(long id);

    Optional<Category> findById(long id);
}
