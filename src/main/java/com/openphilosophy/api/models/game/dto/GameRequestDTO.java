package com.openphilosophy.api.models.game.dto;

import com.openphilosophy.api.models.category.Category;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

public record GameRequestDTO(@NotBlank String title, Set<Category> category, LocalDate release, String synopsis, String developer) {
}
