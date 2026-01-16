package dev.adrian.dragonsapi.service;

import dev.adrian.dragonsapi.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findCategoryById(UUID id);

    Category findCategoryByName(String name);
}
