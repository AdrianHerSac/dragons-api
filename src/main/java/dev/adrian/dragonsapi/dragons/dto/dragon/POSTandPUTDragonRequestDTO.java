package dev.adrian.dragonsapi.dragons.dto.dragon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class POSTandPUTDragonRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;


}