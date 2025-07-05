package io.sparta.board.app.domain.post.infrastructure.exception;

import io.sparta.board.app.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {
	POST_NOT_FOUND("POST_404", "존재하지 않는 게시글입니다", HttpStatus.NOT_FOUND);

	private final String code;
	private final String message;
	private final HttpStatus status;
}
