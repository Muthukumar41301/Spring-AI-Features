package com.demo.spring_ai.Controller;

import com.demo.spring_ai.Service.GeminiAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/gemini")
@RequiredArgsConstructor
public class GeminiAIController {

    private final GeminiAIService geminiAIService;

    @GetMapping("/message")
    public String generate(@RequestParam("message") String message) {
        return geminiAIService.generateResult(message);
    }

    @GetMapping("/check/policy")
    public String checkInsurancePolicy(@RequestParam("message") String message) {
        return geminiAIService.checkInsurancePolicy(message);
    }
}
