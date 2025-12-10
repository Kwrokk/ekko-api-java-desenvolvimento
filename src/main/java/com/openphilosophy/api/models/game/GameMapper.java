package com.openphilosophy.api.models.game;

import com.openphilosophy.api.models.game.dto.GameRequestDTO;
import com.openphilosophy.api.models.game.dto.GameResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Game gameRequestDtoToGame(GameRequestDTO gameRequestDTO);
    GameResponseDTO gameToResponseDTO(Game game);
}
