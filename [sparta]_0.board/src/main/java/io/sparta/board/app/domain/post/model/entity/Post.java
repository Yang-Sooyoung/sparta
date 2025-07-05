package io.sparta.board.app.domain.post.model.entity;

import io.sparta.board.app.domain.comment.model.entity.Comment;
import io.sparta.board.app.domain.post.presentation.dto.request.PostUpdateRequestDto;
import io.sparta.board.app.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "p_post")
@Entity
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "post_id")
	private UUID id;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Comment> commentList = new ArrayList<>();

	public void update(PostUpdateRequestDto postUpdateRequestDto) {
		this.title = postUpdateRequestDto.getTitle();
		this.content = postUpdateRequestDto.getContent();
	}
}
