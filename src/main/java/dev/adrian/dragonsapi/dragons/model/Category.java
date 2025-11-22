package dev.adrian.dragonsapi.dragons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Double speed;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Dragon> dragons;
}