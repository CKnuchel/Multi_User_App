package com.wiss.m223.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PrivateController {
	
	@GetMapping("/private")
	public ResponseEntity<List<String>> getGreeting() {
		return ResponseEntity.ok(List.of("Hello", "Private", "World"));
	}
}
