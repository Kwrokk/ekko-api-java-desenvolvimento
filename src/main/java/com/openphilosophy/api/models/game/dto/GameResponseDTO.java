package com.openphilosophy.api.models.game.dto;

import com.openphilosophy.api.models.category.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record GameResponseDTO(@NotNull UUID id, @NotBlank String title, String developer, LocalDate release, String synopsis, Set<Category> category, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
