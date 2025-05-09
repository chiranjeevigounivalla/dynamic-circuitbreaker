package com.findcode.dynamc_circuitbreaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findcode.dynamc_circuitbreaker.service.AppService;

@RestController
@RequestMapping("/dynamic")
public class Controller {

	@Autowired
	private AppService appService;

	@GetMapping("/getResponse")
	public ResponseEntity<String> callServices(@RequestParam(name = "service", required = true) String service) {

		return appService.registerAndGetResposnce(service);

	}
}
