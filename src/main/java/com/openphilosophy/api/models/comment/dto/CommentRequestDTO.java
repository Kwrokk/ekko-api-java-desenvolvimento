package com.openphilosophy.api.models.comment.dto;

import com.openphilosophy.api.models.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequestDTO(@NotNull User author, @NotBlank String content) {
}