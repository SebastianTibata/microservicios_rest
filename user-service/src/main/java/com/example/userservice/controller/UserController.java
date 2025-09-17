package com.example.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Map<Integer, Map<String,Object>> USERS = new HashMap<>();

    static {
        USERS.put(1, Map.of("id", 1, "name", "Alice"));
        USERS.put(2, Map.of("id", 2, "name", "Bob"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        Map<String,Object> u = USERS.get(id);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u);
    }
}
