package com.openphilosophy.api.models.movie.dto;

import com.openphilosophy.api.models.category.Category;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

public record MovieRequestDTO(@NotBlank String title, String producer, Set<Category> category, LocalDate release, String[] castActors, String synopsis) {
}
