package io.sparta.board.app.domain.comment.infrastructure.exception;

import io.sparta.board.app.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {
	COMMENT_NOT_FOUND("COMMENT_404", "존재하지 않는 댓글입니다", HttpStatus.NOT_FOUND);

	private final String code;
	private final String message;
	private final HttpStatus status;
}
