package com.acme.springbootbootstrapproject.controller;

import com.acme.springbootbootstrapproject.entity.Users;
import com.acme.springbootbootstrapproject.services.TafUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class TafUserController {

    @Autowired
    private TafUserService tafUserService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        Users createdUser = tafUserService.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        Optional<Users> user = tafUserService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users updatedUser) {
        try {
            Users user = tafUserService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}