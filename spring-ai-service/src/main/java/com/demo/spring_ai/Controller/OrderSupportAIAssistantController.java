package com.demo.spring_ai.Controller;

import com.demo.spring_ai.Service.OrderSupportAIAssistantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class OrderSupportAIAssistantController {

    private final OrderSupportAIAssistantService orderSupportAIAssistantService;

    @GetMapping("/order/support")
    public String assistWithOrderSupport(@RequestParam("customerName") String customerName, @RequestParam("orderId") String orderId, @RequestParam("customerMessage") String customerMessage) {
        return orderSupportAIAssistantService.assistWithOrderSupport(customerName, orderId, customerMessage);
    }

}
