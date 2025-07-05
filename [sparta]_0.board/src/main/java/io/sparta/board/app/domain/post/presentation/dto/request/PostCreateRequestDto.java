package io.sparta.board.app.domain.post.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateRequestDto {

	@NotNull(message = "제목은 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "제목은 1~100자로 입력해야 합니다.")
	private String title;
	@NotNull(message = "내용은 필수 입력값입니다.")
	private String content;
}
