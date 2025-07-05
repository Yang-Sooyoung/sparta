package com.sparta.gen_ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AIController {
	private final KafkaTemplate<String, String> kafkaTemplate;

	public AIController(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping("/ask")
	public ResponseEntity<String> askQuestion(@RequestBody String question) {
		kafkaTemplate.send("ai-request", question);
		return ResponseEntity.ok("질문이 전송되었습니다.");
	}
}









