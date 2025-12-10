package com.openphilosophy.api.models.post;

import com.openphilosophy.api.models.post.dto.PostRequestDTO;
import com.openphilosophy.api.models.post.dto.PostResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Post postRequestDtoToPost(PostRequestDTO postRequestDTO);
    PostResponseDTO postToPostResponseDto(Post post);
}
