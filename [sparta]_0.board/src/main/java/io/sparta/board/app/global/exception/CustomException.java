package io.sparta.board.app.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private final ErrorCode errorCode;

	public static CustomException from(ErrorCode errorCode) {
		return new CustomException(errorCode);
	}

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public String getCode() {
		return errorCode.getCode();
	}

	public int getHttpStatus() {
		return errorCode.getHttpStatus();
	}
}
