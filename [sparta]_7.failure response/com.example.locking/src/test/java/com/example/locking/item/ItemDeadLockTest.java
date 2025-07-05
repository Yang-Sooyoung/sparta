package com.example.locking.item;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemDeadLockTest {
	private static final Logger logger = LoggerFactory.getLogger(ItemDeadLockTest.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void testDeadlock() throws InterruptedException {
		// 두 개의 아이템을 생성
		Item item1 = new Item();
		item1.setName("Item 1");
		item1.setQuantity(100);
		itemRepository.save(item1);

		Item item2 = new Item();
		item2.setName("Item 2");
		item2.setQuantity(200);
		itemRepository.save(item2);

		// 스레드 동기화를 위한 CountDownLatch 설정
		CountDownLatch latch = new CountDownLatch(1);
		AtomicReference<Exception> exceptionInThread = new AtomicReference<>();

		Thread thread1 = new Thread(() -> {
			try {
				logger.info("스레드 1 시작");
				latch.await();  // 다른 스레드가 준비될 때까지 대기
				logger.info("스레드 1: 아이템 1 -> 아이템 2 업데이트 시도");
				itemService.updateItemsQuantity(item1.getId(), item2.getId());
				logger.info("스레드 1: 업데이트 완료");
			} catch (TransactionSystemException e) {
				logger.error("스레드 1: 트랜잭션 오류 발생 - {}", e.getMessage());
				exceptionInThread.set(e);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				logger.error("스레드 1: 알 수 없는 오류 발생", e);
				exceptionInThread.set(e);
			}
		});

		Thread thread2 = new Thread(() -> {
			try {
				logger.info("스레드 2 시작");
				logger.info("스레드 2: 아이템 2 -> 아이템 1 업데이트 시도");
				itemService.updateItemsQuantity(item2.getId(), item1.getId());
				logger.info("스레드 2: 업데이트 완료");
			} catch (TransactionSystemException e) {
				logger.error("스레드 2: 트랜잭션 오류 발생 - {}", e.getMessage());
				exceptionInThread.set(e);
			} catch (Exception e) {
				logger.error("스레드 2: 알 수 없는 오류 발생", e);
				exceptionInThread.set(e);
			}
		});

		// 두 스레드를 시작
		thread1.start();
		thread2.start();

		// 스레드가 준비되었음을 알리고 실행
		latch.countDown();

		// 두 스레드가 종료될 때까지 대기
		thread1.join();
		thread2.join();

		// 스레드 중 하나에서 TransactionSystemException이 발생했는지 확인
		assertThrows(Exception.class, () -> {
			if (exceptionInThread.get() != null) {
				throw exceptionInThread.get();
			}
		});
	}
}
