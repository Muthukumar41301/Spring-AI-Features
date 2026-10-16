package com.demo.spring_ai.Controller;

import com.demo.spring_ai.Service.SpringAIService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SpringAIController {

    private final SpringAIService springAIService;

    @Operation(summary = "Generate a response based on a prompt using RAG")
    @GetMapping("/message")
    public String generate(@RequestParam("promptMessage") String promptMessage) {
        return springAIService.generateResult(promptMessage);
    }

    @Operation(summary = "Generate a response based on a prompt using RAG")
    @GetMapping("/check/policy")
    public String checkPolicy(@RequestParam("promptMessage") String promptMessage) {
        return springAIService.checkPolicy(promptMessage);
    }
}
