package dev.adrian.dragonsapi.dragons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * Modelo que representa al dragon
 * con su categoria {@link Category}
 *
 * @see Category
 *
 * @author dev.adrian
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "dragons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String color;

    private String image;

    private String location;

    private LocalDate fechaDeAparicion;

    private LocalDate ultimaAparicion;
}