package com.openphilosophy.api.models.user.dto;

import com.openphilosophy.api.models.user.UserRole;

import java.util.Set;

public record UserResponseDTO(String name, String email, String password, String bio, Set<UserRole> role) {
}
