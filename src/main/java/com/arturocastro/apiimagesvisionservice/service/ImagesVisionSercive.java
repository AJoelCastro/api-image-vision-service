package com.arturocastro.apiimagesvisionservice.service;

import com.arturocastro.apiimagesvisionservice.model.IVModel;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.graders.gradermodels.LabelModelGrader;
import com.openai.models.images.ImageGenerateParams;
import com.openai.models.images.ImageModel;
import com.openai.models.images.ImagesResponse;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseInputItem;
import com.openai.models.responses.ResponseOutputItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImagesVisionSercive {

    private final OpenAIClient client;

    public ImagesVisionSercive(@Value("${openai.api.key}") String apiKey) {
        client = new OpenAIOkHttpClient.Builder()
                .apiKey(apiKey)
                .build();
    }

    public ImagesResponse generateImage (String prompt){

        ImageGenerateParams imageGenerateParams = ImageGenerateParams.builder()
                .model(ImageModel.GPT_IMAGE_1_MINI)
                .n(1)
                .prompt(prompt)
                .size(ImageGenerateParams.Size.AUTO)
                .build();

        return client.images().generate(imageGenerateParams);
    }

    public Response analyzeImage(String uri){
        ResponseCreateParams responseCreateParams = ResponseCreateParams.builder()
                .model(ChatModel.GPT_4_1_MINI)
                .input(
                        ResponseCreateParams.Input
                                .ofText("Dime que personajes ves en la imagen, describelos y sus nombres, basate en la imagen unicamente:" + uri)
                )
                .build();

        return client.responses().create(responseCreateParams);

    }

}
