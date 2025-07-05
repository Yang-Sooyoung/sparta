package io.sparta.board.app.domain.post.presentation.controller;

import io.sparta.board.app.domain.post.application.facade.PostFacade;
import io.sparta.board.app.domain.post.presentation.dto.request.PostCreateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.request.PostUpdateRequestDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostCreatResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostDeleteResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostReadResponseDto;
import io.sparta.board.app.domain.post.presentation.dto.response.PostUpdateResponseDto;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	private final PostFacade postFacade;

	@PostMapping
	public ResponseEntity<PostCreatResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto postCreatRequestDto) {
		return ResponseEntity.ok(postFacade.createPost(postCreatRequestDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostReadResponseDto> readPost(@PathVariable("id")  UUID id, Pageable pageable) {
		return ResponseEntity.ok(postFacade.readPost(id, pageable));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PostUpdateResponseDto> updatePost(@Valid @PathVariable("id") UUID id, @RequestBody
		PostUpdateRequestDto postUpdateRequestDto) {
		return ResponseEntity.ok(postFacade.updatePost(id, postUpdateRequestDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PostDeleteResponseDto> deletePost(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(postFacade.deletePost(id));
	}

}
