package dev.adrian.dragonsapi.dragons.repository;

import dev.adrian.dragonsapi.dragons.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DragonRepository extends JpaRepository {

    Page<Dragon> findAll(Pageable pageable);

}