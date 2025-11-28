package dev.adrian.dragonsapi.dragons.service;

import dev.adrian.dragonsapi.dragons.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findCategoryById(long id);
    Category findCategoryByName(String name);
}
