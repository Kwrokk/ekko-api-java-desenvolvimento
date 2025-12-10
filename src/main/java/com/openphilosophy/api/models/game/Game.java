package com.openphilosophy.api.models.game;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.time.LocalDate;

import com.openphilosophy.api.models.category.Category;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

// TODO: add game image
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "app_games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String title;

    @ElementCollection
    private Set<Category> category;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String synopsis;
    private LocalDate release;
    private String developer;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
