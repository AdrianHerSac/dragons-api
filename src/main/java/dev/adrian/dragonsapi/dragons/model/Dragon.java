package dev.adrian.dragonsapi.dragons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Modelo que representa al dragon {@link Dragon}
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
}