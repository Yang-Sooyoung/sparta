package io.sparta.board.app.domain.post.presentation.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDeleteResponseDto {
	private UUID id;
}
