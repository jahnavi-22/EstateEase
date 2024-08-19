package com.example.EstateEase.controllers;

import com.example.EstateEase.model.User;
import com.example.EstateEase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user){
        User registeredUser = userService.registerUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully.");
        response.put("data", registeredUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (user == null) {
            response.put("message", "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("message", "User retrieved successfully.");
        response.put("data", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<User> users = userService.getUsers();
        Map<String, Object> response = new HashMap<>();
        if (users.isEmpty()) {
            response.put("message", "No users found.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        response.put("message", "Users retrieved successfully.");
        response.put("data", users);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User credentials) {
        User existingUser = userService.findByEmailId(credentials.getEmail()).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (existingUser == null || !existingUser.getPassword().equals(credentials.getPassword())) {
            response.put("message", "Invalid email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        response.put("message", "User logged in successfully.");
        response.put("data", existingUser);
        return ResponseEntity.ok(response);
    }
}
