package com.example.auth.domain.user;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO (
        @NotBlank
        String token
){
}
