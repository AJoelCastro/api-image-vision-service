package com.arturocastro.apiimagesvisionservice.service;

import com.arturocastro.apiimagesvisionservice.DTO.ImageAnalysisRequest;
import com.arturocastro.apiimagesvisionservice.DTO.ImageAnalysisResponse;
import com.arturocastro.apiimagesvisionservice.model.IVModel;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.JsonValue;
import com.openai.models.ChatModel;
import com.openai.models.Reasoning;
import com.openai.models.ReasoningEffort;
import com.openai.models.chat.completions.ChatCompletionContentPartText;
import com.openai.models.graders.gradermodels.LabelModelGrader;
import com.openai.models.images.*;
import com.openai.models.responses.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

    public Response analyzeImage(ImageAnalysisRequest imageAnalysisRequest) throws URISyntaxException {

        ResponseCreateParams responseCreateParams = ResponseCreateParams.builder()
                .model(ChatModel.GPT_5)
                .inputOfResponse(
                        List.of(
                                ResponseInputItem.ofMessage(
                                        ResponseInputItem.Message.builder()
                                                .role(ResponseInputItem.Message.Role.USER)
                                                .addContent(
                                                        ResponseInputText.builder()
                                                                .text(imageAnalysisRequest.getPrompt())
                                                                .build()
                                                )
                                                .addContent(
                                                        ResponseInputImage.builder()
                                                                .detail(
                                                                        ResponseInputImage.Detail.AUTO
                                                                )
                                                                .imageUrl(imageAnalysisRequest.getImageUrl())
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .reasoning(
                        Reasoning.builder()
                                .effort(
                                        ReasoningEffort.MEDIUM
                                )
                                .build()
                )
                .build();
        return client.responses().create(responseCreateParams);

    }

}
