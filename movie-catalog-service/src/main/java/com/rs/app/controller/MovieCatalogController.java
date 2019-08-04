package com.rs.app.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.model.Movie;
import com.rs.app.service.MovieCatalogService;

import lombok.extern.slf4j.Slf4j;

/**
 * Hystrix Dashboard: http://localhost:8082/hystrix
 * Hystrix Stream: http://localhost:8082/actuator/hystrix.stream
 */
@RestController
@RequestMapping(MovieCatalogController.RESOURCE_URI)
@Slf4j
public class MovieCatalogController {
	protected static final String RESOURCE_URI = "/movie/catalog";

	@Autowired
	private MovieCatalogService movieCatalogService;

	@GetMapping("/all")
	public Iterable<Movie> getAvailableMovies() {
		return movieCatalogService.getAvailableMovies();
	}

	@GetMapping("/id/{id}")
	public Movie getMovie(@PathVariable long id) {
		// sleep(3, TimeUnit.SECONDS);
		return movieCatalogService.getMovieById(id);
	}

	@GetMapping("/name/{name}")
	public Movie getMovie(@PathVariable String name) {
		return movieCatalogService.getMovieByName(name);
	}

	private static void sleep(long timeout, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(timeout);
		} catch (InterruptedException ie) {
			log.error(ie.getMessage());
		}
	}
}
