package io.sparta.board.app.domain.comment.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentUpdateResponseDto {
	private UUID id;
	@JsonProperty("post-id")
	private UUID postId;
	private String content;
}
