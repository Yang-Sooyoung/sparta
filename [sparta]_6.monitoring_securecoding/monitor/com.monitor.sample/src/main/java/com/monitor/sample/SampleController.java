package com.monitor.sample;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	@GetMapping("/")
	public String hello(HttpServletResponse response) throws IOException {
		logger.info("Attempted access to / endpoint resulted in 403 Forbidden");
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
		return null;
	}
}
