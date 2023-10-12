package com.example.auth.domain.user;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record RegisterDTO(
        @NotBlank
        String login,

        @NotBlank
        String password,

        @NotBlank
        String role
) {
        public User convert() {
                return new User(this.login(),new BCryptPasswordEncoder().encode(this.password()),UserRole.getRegisterRole(this.role()));
        }
}
