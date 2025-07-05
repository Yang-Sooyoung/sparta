package io.sparta.board.app.domain.post.application.facade;

import io.sparta.board.app.domain.post.application.usecase.PostService;
import io.sparta.board.app.domain.post.presentation.dto.request.PostCreateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.request.PostUpdateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostCreatResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostDeleteResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostReadResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostUpdateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostFacadeImpl implements PostFacade {
	private final PostService postService;

	@Override
	public PostCreatResponseDto createPost(PostCreateRequestDto postCreatRequestDto) {
		return postService.createPost(postCreatRequestDto);
	}
	@Override
	public PostReadResponseDto readPost(UUID id, Pageable pageable) {
		return postService.readPost(id, pageable);
	}

	@Override
	public PostUpdateResponseDto updatePost(UUID id,PostUpdateRequestDto postUpdateRequestDto) {
		return postService.updatePost(id, postUpdateRequestDto);
	}

	@Override
	public PostDeleteResponseDto deletePost(UUID id) {
		return postService.deletePost(id);
	}
}
