package io.sparta.board.app.domain.post.model.repository;

import io.sparta.board.app.domain.post.model.entity.Post;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
