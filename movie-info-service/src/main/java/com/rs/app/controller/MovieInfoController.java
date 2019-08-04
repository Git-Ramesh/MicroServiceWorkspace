package com.rs.app.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rs.app.model.Movie;
import com.rs.app.service.MovieInfoService;

@RestController
@RequestMapping(MovieInfoController.RESOURCE_URI)
public class MovieInfoController {
	protected static final String RESOURCE_URI = "/movie/info";

	@Autowired
	private MovieInfoService movieInfoService;
// Reference: https://github.com/Netflix/Hystrix/wiki/Configuration 
	@GetMapping("/id/{id}")
	@HystrixCommand(
			commandKey = "getMovieById", 
			fallbackMethod = "getMovieInfoByIdFallback",
			commandProperties = {
					@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
					@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			},
			threadPoolKey = "getMovieByIdThreadPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "5"),
					@HystrixProperty(name = "maxQueueSize", value = "10"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "1")
			})
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			System.out.println(headerName + " <========> " + httpServletRequest.getHeader(headerName));
		}
		return ResponseEntity.ok(movieInfoService.getMovie(id));
	}

	@GetMapping("/name/{name}")
	public Movie getMovieByName(@PathVariable("name") String name) {
		return movieInfoService.getMovie(name);
	}

	public ResponseEntity<Movie> getMovieInfoByIdFallback(long id, HttpServletRequest req) {
		Movie movie = new Movie();
		return ResponseEntity.ok(movie);
	}
}
