package com.example.orderservice;

import com.example.orderservice.controller.OrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderControllerTest {

    @Autowired
    OrderController controller;

    @MockBean
    RestTemplate restTemplate;

    @Test
    void contextLoadsAndReturnsUser() {
        when(restTemplate.getForObject("http://user-service:8080/users/1", Map.class))
            .thenReturn(Map.of("id", 1, "name", "Alice"));

        var response = controller.getOrder(1);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        Map body = (Map) response.getBody();
        assertThat(body.get("user")).isNotNull();
    }
}
