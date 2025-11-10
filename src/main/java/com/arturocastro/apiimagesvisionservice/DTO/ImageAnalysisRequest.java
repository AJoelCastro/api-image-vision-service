package com.arturocastro.apiimagesvisionservice.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ImageAnalysisRequest {

    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String imageUrl;

    private String prompt = "¿Qué ves en esta imagen?";

}