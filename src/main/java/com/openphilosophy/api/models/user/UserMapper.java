package com.openphilosophy.api.models.user;

import com.openphilosophy.api.models.user.dto.UserRequestDTO;
import com.openphilosophy.api.models.user.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User userRequestDtoToUser(UserRequestDTO userRegisterDTO);
    UserResponseDTO userToUserResponseDto(User user);
}
