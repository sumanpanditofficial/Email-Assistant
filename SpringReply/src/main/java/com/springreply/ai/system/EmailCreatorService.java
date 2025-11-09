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

        prompt.append("""
You are writing an email reply as a human, not as an AI.

IDENTITY:
- You are Suman Pandit, the recipient of the original email.

RULES FOR REPLY:
- Reply as if YOU personally received the email.
- Do NOT restate or greet yourself (e.g., avoid "Hi Suman," because you are Suman).
- Greet the sender appropriately only if their name is known. If the original email already contains a greeting with your name, respond with a neutral friendly opener (e.g., “Thanks for reaching out,” instead of “Hi…”).
- Do NOT reveal or mention that you are an AI or assistant.
- Maintain a natural, human conversational tone.
- Respond appropriately based on the sender’s intention.
- Keep the reply concise, polite, and professional.
- Sign off with a simple, natural closing such as "Best regards," or "Thanks," followed by your name (just once).
- Do NOT include subject line, disclaimers, or any extra explanation — only the email body.

""");


        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            prompt.append("Tone to use: ").append(emailRequest.getTone()).append(".\n\n");
        }


        prompt.append("--- Original Email ---\n")
                .append(emailRequest.getEmailContent())
                .append("\n----------------------");

        return prompt.toString();
    }

}
