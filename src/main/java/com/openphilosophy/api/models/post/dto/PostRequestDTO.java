package com.openphilosophy.api.models.post.dto;

import com.openphilosophy.api.models.game.Game;
import com.openphilosophy.api.models.movie.Movie;
import com.openphilosophy.api.models.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record PostRequestDTO(@NotBlank String content, @NotNull User author, @NotBlank String title, Set<String> tags, Set<Game> game, Set<Movie> movie) {
}
