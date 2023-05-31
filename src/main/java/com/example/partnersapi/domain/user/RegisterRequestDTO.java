package com.example.partnersapi.domain.user;

public record RegisterRequestDTO(String email, String password, UserRole role) {
}
