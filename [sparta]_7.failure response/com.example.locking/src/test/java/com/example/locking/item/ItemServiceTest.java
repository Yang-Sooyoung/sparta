package com.example.locking.item;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ItemServiceTest.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void testPessimisticLocking() throws InterruptedException {
		logger.info("초기 아이템 데이터 설정");
		Item item = new Item();
		item.setName("Item 1");
		item.setQuantity(10);
		itemRepository.save(item);

		Thread thread1 = new Thread(() -> {
			logger.info("스레드 1: 아이템 수량 업데이트 시도");
			itemService.updateItemQuantity(item.getId(), 20);
			logger.info("스레드 1: 아이템 수량 업데이트 완료");
		});

		Thread thread2 = new Thread(() -> {
			logger.info("스레드 2: 아이템 수량 업데이트 시도");
			itemService.updateItemQuantity(item.getId(), 30);
			logger.info("스레드 2: 아이템 수량 업데이트 완료");
		});

		thread2.start();
		thread1.start();

		thread1.join();
		thread2.join();

		Item updateItem = itemService.findItemById(item.getId());
		logger.info("최종 아이템 수량 : {}", updateItem.getQuantity());



	}
}
