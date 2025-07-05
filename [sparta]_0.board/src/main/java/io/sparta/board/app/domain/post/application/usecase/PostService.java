package io.sparta.board.app.domain.post.application.usecase;

import io.sparta.board.app.domain.post.presentation.dto.request.PostCreateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.request.PostUpdateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostCreatResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostDeleteResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostReadResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostUpdateResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface PostService {

	PostCreatResponseDto createPost(PostCreateRequestDto postCreatRequestDto);

	PostReadResponseDto readPost(UUID id, Pageable pageable);

	PostUpdateResponseDto updatePost(UUID id, PostUpdateRequestDto postUpdateRequestDto);

	PostDeleteResponseDto deletePost(UUID id);
}
