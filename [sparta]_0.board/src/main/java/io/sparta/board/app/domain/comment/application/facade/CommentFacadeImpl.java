package io.sparta.board.app.domain.comment.application.facade;

import io.sparta.board.app.domain.comment.application.usecase.CommentService;
import io.sparta.board.app.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import io.sparta.board.app.domain.comment.presentation.dto.request.CommentUpdateRequestDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentCreateResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentDeleteResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentUpdateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentFacadeImpl implements CommentFacade {
	private final CommentService commentService;

	@Override
	public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto) {
		return commentService.createComment(commentCreateRequestDto);
	}

	@Override
	public CommentUpdateResponseDto updateComment(UUID id,
		CommentUpdateRequestDto commentUpdateRequestDto) {
		return commentService.updateComment(id, commentUpdateRequestDto);
	}

	@Override
	public CommentDeleteResponseDto deleteComment(UUID id) {
		return commentService.deleteComment(id);
	}
}
