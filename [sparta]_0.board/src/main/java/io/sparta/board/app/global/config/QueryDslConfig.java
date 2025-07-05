package io.sparta.board.app.global.config;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {

	private final EntityManager em;

	@Bean
	public JPAQueryFactory jpaQueryFactory() { return new JPAQueryFactory(em); }

	public static OrderSpecifier<?>[] getAllOrderSpecifierArr(Pageable pageable, Path<?> path) {
		List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable, path);
		return orders.toArray(OrderSpecifier[]::new);
	}

	public static List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable, Path<?> path) {
		List<OrderSpecifier<?>> orders = new ArrayList<>();
		if (!isEmpty(pageable.getSort())) {
			for (Sort.Order order : pageable.getSort()) {
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

				OrderSpecifier<?> createdDate
					= getSortedColumn(direction, path, order.getProperty());
				orders.add(createdDate);
			}
		}
		return orders;
	}

	public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
		Path<?> fieldPath = Expressions.path(Object.class, parent, fieldName);

		return new OrderSpecifier(order, fieldPath);
	}

	public static int getUsableSize(int size) {
		switch (size) {
			case 30 -> {
				return 30;
			}
			case 50 -> {
				return 50;
			}
			default -> {
				return 10;
			}
		}
	}
}

