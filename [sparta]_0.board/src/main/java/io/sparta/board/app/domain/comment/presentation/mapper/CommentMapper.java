package io.sparta.board.app.domain.comment.presentation.mapper;

import io.sparta.board.app.domain.comment.model.entity.Comment;
import io.sparta.board.app.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentCreateResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentDeleteResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentUpdateResponseDto;
import io.sparta.board.app.domain.post.model.entity.Post;

public class CommentMapper {

	public static Comment commentCreateRequestDtotoEntity(CommentCreateRequestDto commentCreateRequestDto, Post post) {
		return Comment.builder()
				.post(post)
				.content(commentCreateRequestDto.getContent())
				.build();
	}

	public static CommentCreateResponseDto entityToCreateResponseDto(Comment comment) {
		return CommentCreateResponseDto.builder()
				.id(comment.getId())
				.build();
	}

	public static CommentUpdateResponseDto entityToUpdateResponseDto(Post post, Comment comment) {
		return CommentUpdateResponseDto.builder()
			.id(comment.getId())
			.postId(post.getId())
			.content(comment.getContent())
			.build();
	}

	public static CommentDeleteResponseDto entityToDeleteResponseDto(Comment comment) {
		return CommentDeleteResponseDto.builder()
			.id(comment.getId())
			.build();
	}
}
