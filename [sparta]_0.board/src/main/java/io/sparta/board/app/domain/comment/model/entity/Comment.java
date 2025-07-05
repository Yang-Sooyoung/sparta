package io.sparta.board.app.domain.comment.model.entity;

import io.sparta.board.app.domain.comment.presentation.dto.request.CommentUpdateRequestDto;
import io.sparta.board.app.domain.post.model.entity.Post;
import io.sparta.board.app.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "p_comment")
@Entity
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "comment_id")
	private UUID id;

	@JoinColumn(name = "post_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Post post;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	public void update(Post post, CommentUpdateRequestDto commentUpdateRequestDto) {
		this.post = post;
		this.content = commentUpdateRequestDto.getContent();
	}
}
