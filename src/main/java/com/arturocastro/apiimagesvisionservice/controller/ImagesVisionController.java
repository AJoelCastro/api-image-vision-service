package com.arturocastro.apiimagesvisionservice.controller;

import com.arturocastro.apiimagesvisionservice.model.IVModel;
import com.arturocastro.apiimagesvisionservice.service.ImagesVisionSercive;
import com.openai.models.images.ImagesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/openai/images-vision")
public class ImagesVisionController {

    private final ImagesVisionSercive imagesVisionSercive;

    public ImagesVisionController(ImagesVisionSercive imagesVisionSercive) {
        this.imagesVisionSercive = imagesVisionSercive;
    }

    @PostMapping("/generate-image")
    public ResponseEntity<ImagesResponse> generateImage(@RequestBody IVModel ivModel) throws Exception{
        System.out.println("Inside generateImage method" + ivModel);
        ImagesResponse imagesResponse = imagesVisionSercive.generateImage(ivModel);
        return ResponseEntity.ok(imagesResponse);
    }
}
