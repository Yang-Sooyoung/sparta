package io.sparta.board.app.domain.comment.presentation.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDeleteResponseDto {
	private UUID id;
}
