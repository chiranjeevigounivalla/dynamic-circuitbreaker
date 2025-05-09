package com.findcode.serviceA.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callservice")
public class ServiceAController {
	
	@GetMapping("/a")
	public String callServiceA() {
		
		return "Calling Service A";
	}

}
