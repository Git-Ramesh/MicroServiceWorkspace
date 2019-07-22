package com.rs.app.controller;


import com.rs.app.model.Movie;
import com.rs.app.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Use Dashboard URL
 * http://localhost:8082/actuator/hystrix.stream
 */
@RestController
@RequestMapping(MovieCatalogController.RESOURCE_URI)
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
        return movieCatalogService.getMovieById(id);
    }

    @GetMapping("/name/{name}")
    public Movie getMovie(@PathVariable String name) {
        return movieCatalogService.getMovieByName(name);
    }
}
