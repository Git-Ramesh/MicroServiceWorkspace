package com.rs.app.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rs.app.model.Movie;
import com.rs.app.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping(MovieInfoController.RESOURCE_URI)
public class MovieInfoController {
    protected static final String RESOURCE_URI = "/movie/info";

    @Autowired
    private MovieInfoService movieInfoService;


    @GetMapping("/id/{id}")
    @HystrixCommand(commandKey = "movieById", fallbackMethod = "fallbackError")
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

    public ResponseEntity<Movie> fallbackError(long id, HttpServletRequest req) {
        //String errorMessage = String.format("Unable to get moving having id = %s  \n RequestURI: " + req.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
