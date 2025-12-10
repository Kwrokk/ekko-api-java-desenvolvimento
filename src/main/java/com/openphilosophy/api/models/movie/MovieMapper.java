package com.openphilosophy.api.models.movie;

import com.openphilosophy.api.models.movie.dto.MovieRequestDTO;
import com.openphilosophy.api.models.movie.dto.MovieResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Movie movieRequestDtoToMovie(MovieRequestDTO movieRequestDTO);
    MovieResponseDTO movieToMovieResponseDto(Movie movie);
}
