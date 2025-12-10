package com.openphilosophy.api.models.movie.dto;

import com.openphilosophy.api.models.category.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record MovieResponseDTO(@NotNull UUID id, @NotBlank String title, String producer, LocalDate release, Set<Category> category, String[] castActors, String synopsis, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
