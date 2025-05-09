package com.findcode.dynamc_circuitbreaker.service;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.decorators.Decorators;

@Service
public class AppService {

	@Autowired
	private RestTemplate restTemplate;
	
	private CircuitBreakerRegistry circuitBreakerRegistry;

	public AppService(CircuitBreakerRegistry circuitBreakerRegistry) {
		this.circuitBreakerRegistry = circuitBreakerRegistry;

	}

	public ResponseEntity<String> registerAndGetResposnce(String service) {
		// Register Name in circuitBreakerRegistry
		CircuitBreaker circuteBreaker = circuitBreakerRegistry.circuitBreaker("Service-" + service.toUpperCase());

		Supplier<ResponseEntity<String>> apiCall = () -> callAPI(service);
		Supplier<ResponseEntity<String>> decoratedAPICall = Decorators.ofSupplier(apiCall)
				.withCircuitBreaker(circuteBreaker).withFallback(this::fallBack).decorate();
		return decoratedAPICall.get();
	}

	private ResponseEntity<String> callAPI(String service) {
		String url = createTamsUrl(service);
		return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
	}

	private String createTamsUrl(String service) {
		// Note: Generally we will call host names and integrated API;
		// As we are Running in local I created the different services Service A,
		// Service B and Service C
		// Now planning prepare URL by passing the port name and service name
		// You can customize the URl according to the Business
		String port = getport(service);
		return "http://localhost:" + port + "/callservice" + "/" + service;
	}

	private String getport(String service) {
		return switch (service) {
		case "a" -> "8081";
		case "b" -> "8082";
		case "c" -> "8083";
		default -> "Unknown";
		};
	}

	public ResponseEntity<String> fallBack(Throwable throwable) {
		System.out.println(
				"Connection Exception recovered at:" + LocalDateTime.now() + " Reason: " + throwable.getMessage());
		return new ResponseEntity<>(HttpStatusCode.valueOf(404));
	}

}
