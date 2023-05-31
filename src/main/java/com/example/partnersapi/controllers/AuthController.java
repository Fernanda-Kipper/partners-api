package com.example.partnersapi.controllers;

import com.example.partnersapi.domain.user.*;
import com.example.partnersapi.infra.security.TokenService;
import com.example.partnersapi.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticationRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO data){
        if(this.repository.findByLogin(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
