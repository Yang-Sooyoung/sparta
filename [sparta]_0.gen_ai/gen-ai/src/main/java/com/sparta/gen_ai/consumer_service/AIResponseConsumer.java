package com.sparta.gen_ai.consumer_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AIResponseConsumer {

	private final WebSocketHandler socketHandler;

	public AIResponseConsumer(WebSocketHandler socketHandler) {
		this.socketHandler = socketHandler;
	}

	@KafkaListener(topics = "ai-response", groupId = "ai-group")
	public void handleResponse(String response) {
		socketHandler.broadcast(response); // WebSocket으로 전송
	}
}