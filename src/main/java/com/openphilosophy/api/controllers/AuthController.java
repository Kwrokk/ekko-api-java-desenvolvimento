package com.openphilosophy.api.controllers;

import com.openphilosophy.api.models.user.dto.LoginRequestDTO;
import com.openphilosophy.api.models.user.User;
import com.openphilosophy.api.models.user.dto.UserRequestDTO;
import com.openphilosophy.api.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody @Valid UserRequestDTO data) {
        return ResponseEntity.ok(authService.createUser(data));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO body) {
        return ResponseEntity.ok(authService.login(body));
    }

}
