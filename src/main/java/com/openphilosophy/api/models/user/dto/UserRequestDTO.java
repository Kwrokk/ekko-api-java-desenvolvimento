package com.openphilosophy.api.models.user.dto;

import com.openphilosophy.api.models.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserRequestDTO(@NotBlank String name, @NotBlank @Email String email, @NotBlank String password, @NotNull Set<UserRole> role, String bio) {
}
