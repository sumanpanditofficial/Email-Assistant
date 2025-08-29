package com.springreply.ai.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailCreatorService {

    private final WebClient webClient;

    @Value("${Gemini.api.url}")
    private String geminiUrl;

    @Value("${gemini.api.key}")
    private String geminiKey;

    public EmailCreatorService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }


    //Mapping the prompt
    public String emailReply(EmailRequest emailRequest) {

        String prompt = buildPrompt(emailRequest);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        //Sending the post request to the gemini
        String response = webClient.post()
                .uri(geminiUrl)
                .header("Content-Type", "application/json")
                .header("X-goog-api-key", geminiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return exactResponseContent(response);

    }

    private String exactResponseContent(String response) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch (Exception e) {
            return "Some error occurred" + e.getMessage();
        }
    }

    //Building Prompt
    public String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("Generate only the email body as a reply to the following email content. ")
                .append("Do not generate a subject line, explanations, notes, or any additional text. ")
                .append("The output must only contain the email body. ");

        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            prompt.append("Write the reply in a ")
                    .append(emailRequest.getTone())
                    .append(" tone. ");
        }

        prompt.append("\n--- Original Email ---\n")
                .append(emailRequest.getEmailContent())
                .append("\n----------------------");

        return prompt.toString();
    }

}
