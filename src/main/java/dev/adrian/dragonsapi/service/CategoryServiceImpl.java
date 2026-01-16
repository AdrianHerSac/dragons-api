package dev.adrian.dragonsapi.service;

import dev.adrian.dragonsapi.model.Category;
import dev.adrian.dragonsapi.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        log.info("Finding all categories");
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findCategoryById(UUID id) {
        log.info("Finding category with id {}", id);
        return categoryRepository.findById(id);
    }

    @Override
    public Category findCategoryByName(String name) {
        return null;
    }
}
