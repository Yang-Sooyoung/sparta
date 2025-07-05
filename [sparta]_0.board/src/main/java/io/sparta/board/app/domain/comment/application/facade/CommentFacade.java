package io.sparta.board.app.domain.comment.application.facade;

import io.sparta.board.app.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import io.sparta.board.app.domain.comment.presentation.dto.request.CommentUpdateRequestDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentCreateResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentDeleteResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentUpdateResponseDto;
import java.util.UUID;

public interface CommentFacade {
	CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto);

	CommentUpdateResponseDto updateComment(UUID id, CommentUpdateRequestDto commentUpdateRequestDto);

	CommentDeleteResponseDto deleteComment(UUID id);
}
