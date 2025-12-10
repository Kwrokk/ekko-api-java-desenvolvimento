package com.openphilosophy.api.models.post.dto;

import com.openphilosophy.api.models.comment.Comment;
import com.openphilosophy.api.models.game.Game;
import com.openphilosophy.api.models.movie.Movie;
import com.openphilosophy.api.models.post.PostReaction;
import com.openphilosophy.api.models.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record PostResponseDTO(UUID id, String title, String content, User author, Set<String> tags, Set<Game> game, Set<Movie> movie, List<Comment> comments, List<PostReaction> reactions, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
