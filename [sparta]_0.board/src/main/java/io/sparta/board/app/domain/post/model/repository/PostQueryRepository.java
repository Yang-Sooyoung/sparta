package io.sparta.board.app.domain.post.model.repository;

import static io.sparta.board.app.domain.comment.model.entity.QComment.comment;
import static io.sparta.board.app.global.config.QueryDslConfig.getUsableSize;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.sparta.board.app.domain.comment.model.entity.Comment;
import io.sparta.board.app.global.config.QueryDslConfig;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public Page<Comment> findPostCommentList(UUID id, Pageable pageable) {

		OrderSpecifier<?>[] orderSpecifiers = QueryDslConfig.getAllOrderSpecifierArr(pageable, comment);

		List<Comment> contents = jpaQueryFactory
			.selectFrom(comment)
			.where(
				comment.post.id.eq(id),
				comment.deleted.eq(false)
			)
			.orderBy(orderSpecifiers)
			.offset(pageable.getOffset())
			.limit(getUsableSize(pageable.getPageSize()))
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(comment.count())
			.from(comment)
			.where(
				comment.post.id.eq(id),
				comment.deleted.eq(false)
			);

		return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
	}
}
