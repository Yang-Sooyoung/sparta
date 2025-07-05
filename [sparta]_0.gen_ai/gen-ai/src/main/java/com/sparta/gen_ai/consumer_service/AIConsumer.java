package com.sparta.gen_ai.consumer_service;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AIConsumer {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final VertexAiGeminiChatModel vertexAiGeminiChatModel;

	public AIConsumer(KafkaTemplate<String, String> kafkaTemplate, VertexAiGeminiChatModel vertexAiGeminiChatModel) {
		this.kafkaTemplate = kafkaTemplate;
		this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
	}

	@KafkaListener(topics = "ai-request", groupId = "ai-group")
	public void handleMessage(String question) {
		Prompt prompt = new Prompt(question);
		vertexAiGeminiChatModel
			.stream(prompt)
			.doOnNext(res -> {
				String token = res.getResult().getOutput().getText();
				try {
					Thread.sleep(new Random().nextInt(50, 500)); // 50ms 500ms 랜덤 딜레이
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				kafkaTemplate.send("ai-response", token);
			})
			.doOnComplete(() -> {
				kafkaTemplate.send("ai-response", "\n"); // 개행 처리
			})
			.doOnError(e -> {
				System.err.println("에러 발생: " + e.getMessage());
			})
			.subscribe();
	}
}