package com.findcode.serviceB.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callservice")
public class ServiceBController {
	
	@GetMapping("/b")
	public String callServiceB() {
		
		return "Calling Service B";
	}

}
