package com.demo.spring_ai.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class GeminiAIService {

    private final ChatClient chatClient;

    @Value("classpath:prompts/insurance_policy_system_temlpate.st")
    private Resource insuranceSystemTemplate;

    private static final String CLAIM_DETAILS="Claim Details:" +
            "Max Coverage: 10000"+
            "Claim Amount: 30000";

    public GeminiAIService(GoogleGenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    public String generateResult(String message) {

        return chatClient
                .prompt(message)
                .call()
                .content();
    }

    public String checkInsurancePolicy(String message) {

        return chatClient
                .prompt()
                .system(insuranceSystemTemplate)
                .user("""
                    Customer claim details:
                    %s

                    Customer message:
                    %s
                    """.formatted(CLAIM_DETAILS, message))
                .call()
                .content();
    }

    public Flux<String> askToAIStream(String message) {
        return chatClient.prompt(message).stream().content();
    }
}
