package dev.adrian.dragonsapi.dragons.dto.dragon;

import dev.adrian.dragonsapi.dragons.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GENERICDragonResponseDTO {

    private UUID id;

    private String name;

    private String color;

    private String image;

    private String location;

    private Category category;
}