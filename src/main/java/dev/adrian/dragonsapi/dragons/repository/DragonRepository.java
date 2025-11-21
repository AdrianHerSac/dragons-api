package dev.adrian.dragonsapi.dragons.repository;

import dev.adrian.dragonsapi.dragons.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DragonRepository extends JpaRepository<Dragon, UUID> {

    Page<Dragon> findByCategoryNameIgnoreCase(String categoryName, Pageable pageable);

}