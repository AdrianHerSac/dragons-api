package dev.adrian.dragonsapi.dragons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
@Table(name = "dragon_plushies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String name;

    private String color;

    private String image;

    @Enumerated(EnumType.STRING)
    private Size size;

    private BigDecimal price;

    private int stock;

    public enum Size {
        S, M, L, XL
    }
}