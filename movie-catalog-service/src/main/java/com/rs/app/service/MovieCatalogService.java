package com.rs.app.service;

import com.rs.app.model.Movie;

public interface MovieCatalogService {
    Iterable<Movie> getAvailableMovies();

    Movie getMovieById(long id);

    Movie getMovieByName(String name);
}
