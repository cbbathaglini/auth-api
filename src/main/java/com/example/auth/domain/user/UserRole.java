package com.example.auth.domain.user;

public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole getRegisterRole(String role){
        return UserRole.valueOf(role);
    }
}
