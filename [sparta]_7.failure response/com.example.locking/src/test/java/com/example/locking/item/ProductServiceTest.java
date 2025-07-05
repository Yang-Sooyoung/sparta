package com.example.locking.item;

import com.example.locking.product.Product;
import com.example.locking.product.ProductRepository;
import com.example.locking.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductServiceTest {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testOptimisticLocking() throws InterruptedException {
		// 초기 데이터 설정
		Product product = new Product();
		product.setName("Product 1");
		product.setPrice(100.0);
		productRepository.save(product);

		// 첫 번째 트랜잭션: 상품 가격을 200.0으로 업데이트
		Thread thread1 = new Thread(() -> {
			productService.updateProductPrice(product.getId(), 200.0);
		});

		// 두 번째 트랜잭션: 상품 가격을 300.0으로 업데이트
		Thread thread2 = new Thread(() -> {
			assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
				productService.updateProductPrice(product.getId(), 300.0);
			});
		});

		// 두 스레드를 동시에 실행
		thread1.start();
		thread2.start();

		// 두 스레드가 종료될 때까지 대기
		thread1.join();
		thread2.join();
	}
}
