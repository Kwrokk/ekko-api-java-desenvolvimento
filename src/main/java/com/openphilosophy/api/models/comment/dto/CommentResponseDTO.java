package com.openphilosophy.api.models.comment.dto;

import com.openphilosophy.api.models.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponseDTO(@NotNull UUID id, @NotNull User author, @NotBlank String content, @NotNull LocalDateTime createdAt, @NotNull LocalDateTime updatedAt) {
}
