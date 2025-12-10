package com.openphilosophy.api.controllers;


import com.openphilosophy.api.models.user.User;
import jakarta.validation.Valid;
import com.openphilosophy.api.models.user.dto.UserResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openphilosophy.api.services.UserService;
import com.openphilosophy.api.models.user.dto.UserRequestDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
         this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@PathVariable String id, @RequestBody UserRequestDTO data) {
        return ResponseEntity.ok(userService.update(id, data));
    }

    // TODO: update userService.delete method (it's better if it returns somethings instead of the endpoint itself)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        userService.delete(UUID.fromString(id))     ;
        return ResponseEntity.ok("ok");
    }
}
