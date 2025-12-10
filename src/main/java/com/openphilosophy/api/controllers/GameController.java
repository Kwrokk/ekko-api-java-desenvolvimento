package com.openphilosophy.api.controllers;

import com.openphilosophy.api.models.game.Game;
import jakarta.validation.Valid;
import com.openphilosophy.api.services.GameService;
import com.openphilosophy.api.models.game.dto.GameRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/")
    public ResponseEntity<Game> register(@RequestBody @Valid GameRequestDTO data) {
        return ResponseEntity.ok(gameService.register(data));
    }

    @GetMapping("/")
    public ResponseEntity<List<Game>> findAll() {
        return ResponseEntity.ok(gameService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameData(@PathVariable String id) {
        return ResponseEntity.ok(gameService.getById(id));
    }

    // TODO: implement update method
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid GameRequestDTO data) {
        return ResponseEntity.ok(gameService.update(id, data));
    }

    // TODO: implement delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(gameService.deleteById(id));
    }
}
