package com.openphilosophy.api.controllers;

import com.openphilosophy.api.models.movie.Movie;
import com.openphilosophy.api.models.movie.dto.MovieRequestDTO;
import com.openphilosophy.api.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/")
    public ResponseEntity<Movie> create(@RequestBody @Valid MovieRequestDTO data) {
        return ResponseEntity.ok(movieService.create(data));
    }

    // TODO: implement getById method
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    // TODO: implement update method
    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable String id, @RequestBody @Valid MovieRequestDTO data) {
        return ResponseEntity.ok(movieService.updateById(id, data));
    }

    // TODO: implement delete method
}
