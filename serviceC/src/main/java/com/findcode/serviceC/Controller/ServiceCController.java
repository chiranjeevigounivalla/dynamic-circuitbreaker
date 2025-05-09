package com.findcode.serviceC.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callservice")
public class ServiceCController {
	
	@GetMapping("/c")
	public String callServiceC() {
		
		return "Calling Service C";
	}

}
