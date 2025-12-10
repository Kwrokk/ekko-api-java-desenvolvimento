package com.openphilosophy.api.services;

import com.openphilosophy.api.models.game.GameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.openphilosophy.api.models.game.Game;
import com.openphilosophy.api.models.game.dto.GameRequestDTO;
import com.openphilosophy.api.repositories.GameRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

// TODO: refactor functions to handle errors (try-catch logic)
@Component
public class GameService {

    private final Logger logger;
    private final GameMapper gameMapper;
    private final GameRepository gameRepository;

    public GameService(GameMapper gameMapper, GameRepository gameRepository) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.logger = LoggerFactory.getLogger(GameService.class);
    }

    public Game register(GameRequestDTO data) {
        Game game = gameMapper.gameRequestDtoToGame(data);
        return gameRepository.save(game);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game getById(String id) {
        UUID gameId = UUID.fromString(id);
        return gameRepository.findById(gameId).orElse(null);
    }

    public Game update(String id, GameRequestDTO data) throws ResponseStatusException {

        try {
            UUID uuidGame = UUID.fromString(id);
            if (gameRepository.findById(uuidGame).isPresent()) {
                Game game = gameMapper.gameRequestDtoToGame(data);
                game.setId(uuidGame);
                gameRepository.save(game);
                return game;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found.");
            }
        } catch (Exception e) {
            logger.error("There was an unexpected error while trying to update this game's info.");
            logger.error("INFO: ", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an unexpected error while trying to update this game's info. Please review your request and try again.");
        }
    }

    public String deleteById(String id) {
        try {
            UUID gameId = UUID.fromString(id);
            if (gameRepository.findById(gameId).isPresent()) {
                gameRepository.deleteById(gameId);
                return "success";
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
        }
    }
}
