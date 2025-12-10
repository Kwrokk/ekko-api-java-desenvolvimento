package com.openphilosophy.api.models.post;

import com.openphilosophy.api.models.game.Game;
import com.openphilosophy.api.models.movie.Movie;
import com.openphilosophy.api.models.comment.Comment;
import com.openphilosophy.api.models.category.Category;
import com.openphilosophy.api.models.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "app_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> game;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Movie> movie;

    @ElementCollection
    private Set<String> tags;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String content;

    @ElementCollection
    @ManyToMany
    private Set<Category> category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ElementCollection
    private List<PostReaction> reactions;

    @ElementCollection
    private List<Comment> comments;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
