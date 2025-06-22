package com.awbd.musicshop.controllers;

import com.awbd.musicshop.domain.User;
import com.awbd.musicshop.dtos.RegisterDTO;
import com.awbd.musicshop.services.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO request) {
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }

}
