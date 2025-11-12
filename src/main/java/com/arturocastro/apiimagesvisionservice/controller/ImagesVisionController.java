package com.arturocastro.apiimagesvisionservice.controller;

import com.arturocastro.apiimagesvisionservice.DTO.ImageAnalysisRequest;
import com.arturocastro.apiimagesvisionservice.model.IVModel;
import com.arturocastro.apiimagesvisionservice.service.ImagesVisionSercive;
import com.openai.models.images.ImagesResponse;
import com.openai.models.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/openai/images-vision")
public class ImagesVisionController {

    private final ImagesVisionSercive imagesVisionSercive;

    public ImagesVisionController(ImagesVisionSercive imagesVisionSercive) {
        this.imagesVisionSercive = imagesVisionSercive;
    }

    @PostMapping("/generate-image")
    public ResponseEntity<ImagesResponse> generateImage(@RequestBody String prompt) throws Exception{
        ImagesResponse imagesResponse = imagesVisionSercive.generateImage(prompt);
        return ResponseEntity.ok(imagesResponse);
    }

    @PostMapping("analyze-image")
    public ResponseEntity<Response> analyzeImage(@RequestBody ImageAnalysisRequest imageAnalysisRequest) throws Exception{
        Response response = imagesVisionSercive.analyzeImage(imageAnalysisRequest);
        return ResponseEntity.ok(response);
    }

}
