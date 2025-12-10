package com.openphilosophy.api.services;

import com.openphilosophy.api.models.movie.Movie;
import com.openphilosophy.api.models.movie.MovieMapper;
import com.openphilosophy.api.models.movie.dto.MovieRequestDTO;
import com.openphilosophy.api.repositories.MovieRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;

// TODO: implement delete
@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    public MovieService(MovieMapper movieMapper, MovieRepository movieRepository) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie getById(String id) throws ResponseStatusException {
        try {
            UUID uuidMovie = UUID.fromString(id);
            return movieRepository.findById(uuidMovie).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found"));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID. Please review your info and ensure the passed ID matches UUID format.");
        }
    }

    public Movie create(MovieRequestDTO data) {
        try {
            Movie movie = movieMapper.movieRequestDtoToMovie(data);
            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new RuntimeException("There was an error while trying to save this movie.");
        }
    }

    public Movie updateById(String id, MovieRequestDTO data) {
        try {
            UUID uuidMovie = UUID.fromString(id);
            if (movieRepository.findById(uuidMovie).isPresent()) {
                Movie movie = movieMapper.movieRequestDtoToMovie(data);
                movie.setId(uuidMovie);
                return movieRepository.save(movie);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
            }
        } catch (Exception e) {
            logger.error("There was an error while trying to parse String (id) to UUID (uuid)");
            logger.error("INFO: ", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an unexpected error while trying to update this movie's info. Please review your request and try again.");
        }
    }

    // TODO: implement delete
}
