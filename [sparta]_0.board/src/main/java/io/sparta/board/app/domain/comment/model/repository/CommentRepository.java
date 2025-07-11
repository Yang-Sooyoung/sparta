package io.sparta.board.app.domain.comment.model.repository;

import io.sparta.board.app.domain.comment.model.entity.Comment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
