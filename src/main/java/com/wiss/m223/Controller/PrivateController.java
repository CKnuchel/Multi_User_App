package com.wiss.m223.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateController {
	
	@GetMapping("/private")
	public ResponseEntity<String> getGreeting() {
		return ResponseEntity.ok("Hello Private World");
	} 
	
}
