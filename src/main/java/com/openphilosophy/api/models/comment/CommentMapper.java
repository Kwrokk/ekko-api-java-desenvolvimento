package com.openphilosophy.api.models.comment;

import com.openphilosophy.api.models.comment.dto.CommentRequestDTO;
import com.openphilosophy.api.models.comment.dto.CommentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment commentRequestDtoToComment(CommentRequestDTO commentRequestDTO);
    CommentResponseDTO commentToCommentResponseDto(Comment comment);
}
