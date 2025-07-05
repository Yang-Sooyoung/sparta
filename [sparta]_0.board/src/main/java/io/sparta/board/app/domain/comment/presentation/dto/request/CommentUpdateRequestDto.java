package io.sparta.board.app.domain.comment.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentUpdateRequestDto {
	@NotNull(message = "게시글 ID는 필수 입력값입니다.")
	@JsonProperty("post-id")
	private UUID postId;

	@NotNull(message = "내용은 필수 입력값입니다.")
	private String content;
}
