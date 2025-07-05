package com.example.locking.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public void updateItemQuantity(Long itemId, Integer newQuantity) {
		// 비관적 락을 사용하여 데이터를 조회합니다.
		Item item = itemRepository.findByIdWithLock(itemId);

		// 재고 수량을 수정합니다.
		item.setQuantity(newQuantity);

		// 수정된 데이터를 저장합니다.
		itemRepository.save(item);
	}

	@Transactional
	public Item findItemById(Long itemId) {
		// 비관적 락 없이 데이터를 조회합니다.
		return itemRepository.findById(itemId).orElse(null);
	}

	@Transactional(timeout = 1, isolation = Isolation.SERIALIZABLE)
	public void updateItemsQuantity(Long itemId1, Long itemId2) {
		// 첫 번째 아이템에 락을 건 후 업데이트
		Item item1 = itemRepository.findByIdWithLock(itemId1);
		item1.setQuantity(item1.getQuantity() + 10);
		itemRepository.save(item1);

		// 잠시 대기
		try { Thread.sleep(4000); } catch (InterruptedException e) { e.printStackTrace(); }

		// 두 번째 아이템에 락을 건 후 업데이트
		Item item2 = itemRepository.findByIdWithLock(itemId2);
		item2.setQuantity(item2.getQuantity() + 10);
		itemRepository.save(item2);
	}
}
