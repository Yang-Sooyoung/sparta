package com.security.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController {

	@GetMapping("/")
	public String showForm() {
		return "form";
	}

	@PostMapping("/submit")
	public String handleFormSubmit(@RequestParam("name") String name, @RequestParam("_csrf") String csrfToken) {
		System.out.println("Received CSRF token: " + csrfToken);
		System.out.println("Received name: " + name);
		return "result";
	}
}
