package io.sparta.board.app.domain.post.application.usecase;

import io.sparta.board.app.domain.comment.model.entity.Comment;
import io.sparta.board.app.domain.post.infrastructure.exception.PostErrorCode;
import io.sparta.board.app.domain.post.model.entity.Post;
import io.sparta.board.app.domain.post.model.repository.PostQueryRepository;
import io.sparta.board.app.domain.post.model.repository.PostRepository;
import io.sparta.board.app.domain.post.presentation.dto.request.PostCreateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.request.PostUpdateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostCreatResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostDeleteResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostReadResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostUpdateResponseDto;
import io.sparta.board.app.domain.post.presentation.mapper.PostMapper;
import io.sparta.board.app.global.exception.CustomException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final PostQueryRepository postQueryRepository;

	@Transactional
	@Override
	public PostCreatResponseDto createPost(PostCreateRequestDto postCreatRequestDto) {
		Post newPost = PostMapper.postCreatRequestDtoToEntity(postCreatRequestDto);
		Post createdPost = postRepository.save(newPost);

		return PostMapper.EntityToCreateResponseDto(createdPost);
	}

	@Transactional(readOnly = true)
	@Override
	public PostReadResponseDto readPost(UUID id, Pageable pageable) {
		Page<Comment> postCommentList = postQueryRepository.findPostCommentList(id, pageable);
		return PostMapper.pageToReadResponseDto(postCommentList);
	}

	@Transactional
	@Override
	public PostUpdateResponseDto updatePost(UUID id, PostUpdateRequestDto postUpdateRequestDto) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));
		post.update(postUpdateRequestDto);
		return PostMapper.EntityToUpdateResponseDto(post);
	}

	@Transactional
	@Override
	public PostDeleteResponseDto deletePost(UUID id) {
		boolean deleted = true;
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));
		post.delete(deleted);
		return PostMapper.entityToDeleteResponseDto(post);
	}
}
