package io.sparta.board.app.domain.post.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReadResponseDto {
	@JsonProperty("total-contents")
	private Long totalContents;

	private Integer size;

	@JsonProperty("current-page")
	private Integer currentPage;


	@JsonProperty("comment-list")
	private List<CommentDto> commentList;

	@Getter
	@Builder
	public static class CommentDto {
		private UUID id;
		@JsonProperty("post-id")
		private UUID postId;
		private String content;
	}
}
