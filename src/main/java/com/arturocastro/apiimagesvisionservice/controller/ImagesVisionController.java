package com.arturocastro.apiimagesvisionservice.controller;

import com.arturocastro.apiimagesvisionservice.model.IVModel;
import com.arturocastro.apiimagesvisionservice.service.ImagesVisionSercive;
import com.openai.models.images.ImagesResponse;
import com.openai.models.responses.Response;
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
    public ResponseEntity<ImagesResponse> generateImage(@RequestBody String prompt) throws Exception{
        System.out.println("Inside generateImage method" + prompt);
        ImagesResponse imagesResponse = imagesVisionSercive.generateImage(prompt);
        return ResponseEntity.ok(imagesResponse);
    }

    @PostMapping("analyze-image")
    public ResponseEntity<Response> analyzeImage(@RequestBody String uri) throws Exception{
        Response response = imagesVisionSercive.analyzeImage(uri);
        return ResponseEntity.ok(response);
    }

}
