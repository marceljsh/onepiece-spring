package com.marceljsh.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.helper.Bundler;

@RestController
@RequestMapping("/ping")
public class PingController {
	/**
	 * Handles the "/ping" endpoint and returns a map with a message indicating that
	 * the host is reachable.
	 *
	 * @return a map containing the message
	 */
	@GetMapping
	public Map<String, Object> ping() {
		return Bundler.pack("message", "host is reachable");
	}
}
