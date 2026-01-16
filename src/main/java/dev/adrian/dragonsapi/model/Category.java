package dev.adrian.dragonsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Modelo que representa la categoria del dragon {@link Dragon}
 *
 * @see Dragon
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 100)
    private String name;

    private String description;
}