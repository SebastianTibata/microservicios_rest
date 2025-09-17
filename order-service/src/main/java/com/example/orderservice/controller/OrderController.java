package com.example.orderservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    private static final Map<Integer, Map<String,Object>> ORDERS = new HashMap<>();

    static {
        ORDERS.put(1, Map.of("id", 1, "user_id", 1, "item", "Laptop"));
        ORDERS.put(2, Map.of("id", 2, "user_id", 2, "item", "Phone"));
    }

    public OrderController(RestTemplate restTemplate, @Value("${USER_SERVICE_URL:http://user-service:8080}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        Map<String,Object> order = ORDERS.get(id);
        if (order == null) return ResponseEntity.notFound().build();

        Integer userId = (Integer) order.get("user_id");
        String url = String.format("%s/users/%d", userServiceUrl, userId);

        Map user = restTemplate.getForObject(url, Map.class);

        Map<String,Object> resp = Map.of("order", order, "user", user);
        return ResponseEntity.ok(resp);
    }
}
