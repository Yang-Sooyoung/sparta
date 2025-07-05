package io.sparta.board.app.domain.comment.application.usecase;

import io.sparta.board.app.domain.comment.infrastructure.exception.CommentErrorCode;
import io.sparta.board.app.domain.comment.model.entity.Comment;
import io.sparta.board.app.domain.comment.model.repository.CommentRepository;
import io.sparta.board.app.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import io.sparta.board.app.domain.comment.presentation.dto.request.CommentUpdateRequestDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentCreateResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentDeleteResponseDto;
import io.sparta.board.app.domain.comment.presentation.dto.response.CommentUpdateResponseDto;
import io.sparta.board.app.domain.comment.presentation.mapper.CommentMapper;
import io.sparta.board.app.domain.post.infrastructure.exception.PostErrorCode;
import io.sparta.board.app.domain.post.model.entity.Post;
import io.sparta.board.app.domain.post.model.repository.PostRepository;
import io.sparta.board.app.global.exception.CustomException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	@Override
	public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto) {
		UUID postId = commentCreateRequestDto.getPostId();
		Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(
			PostErrorCode.POST_NOT_FOUND));

		Comment newComment = CommentMapper.commentCreateRequestDtotoEntity(commentCreateRequestDto, post);
		Comment createdComment = commentRepository.save(newComment);
		return CommentMapper.entityToCreateResponseDto(createdComment);
	}

	@Override
	public CommentUpdateResponseDto updateComment(UUID id, CommentUpdateRequestDto commentUpdateRequestDto) {
		UUID postId = commentUpdateRequestDto.getPostId();
		Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));
		comment.update(post, commentUpdateRequestDto);
		return CommentMapper.entityToUpdateResponseDto(post, comment);
	}

	@Override
	public CommentDeleteResponseDto deleteComment(UUID id) {
		boolean deleted = true;
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));
		comment.delete(deleted);

		return CommentMapper.entityToDeleteResponseDto(comment);
	}
}
