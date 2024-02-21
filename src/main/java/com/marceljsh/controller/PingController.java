package com.marceljsh.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.helper.Bundler;

@RestController
@RequestMapping
public class PingController {

	/**
	 * Handles GET requests to the /ping endpoint.
	 * 
	 * @return A map containing the message "host is reachable".
	 */
	@GetMapping("/ping")
	public Map<String, Object> ping() {
		return Bundler.pack("message", "host is reachable");
	}

	@GetMapping
	public Map<String, Object> defaultEndpoint() {
		return Bundler.pack("message", "one piece api");
	}
}
