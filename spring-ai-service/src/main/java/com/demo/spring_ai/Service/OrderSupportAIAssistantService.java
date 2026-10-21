package com.demo.spring_ai.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSupportAIAssistantService {

    private final ChatClient chatClient;

    @Value("classpath:prompts/order_system_temlpate.st")
    private Resource orderSystemTemplate;

    @Value("classpath:prompts/order_user_temlpate.st")
    private Resource orderUserTemplate;

    public OrderSupportAIAssistantService(GoogleGenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    public String assistWithOrderSupport(String customerName, String orderId, String customerMessage) {

        return chatClient
                .prompt()
                .advisors(List.of(new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(List.of("password","otp", "cvv"),"Your request contains sensitive information. For your security, please remove passwords, OTPs, credentials, or other confidential details and try again.",1)))
                .system(orderSystemTemplate)
                .user(promptUserSpec ->
                        promptUserSpec.text(orderUserTemplate).param("customerName", customerName)
                                .param("orderId", orderId)
                                .param("customerMessage", customerMessage)
                )
                .call()
                .content();
    }
}
