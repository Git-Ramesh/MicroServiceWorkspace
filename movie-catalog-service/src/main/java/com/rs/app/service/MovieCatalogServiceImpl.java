package com.rs.app.service;

import com.rs.app.model.Movie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class MovieCatalogServiceImpl implements MovieCatalogService {
    private static List<Movie> movies;

    static {
        movies = Arrays.asList(
                new Movie(1L, "GoldenEye", " Pierce Brosnan", " Martin Campbell"),
                new Movie(2L, "The World Is Not Enough", "Pierce Brosnan", "Michael Apted"),
                new Movie(3L, " Die Another Day", "Pierce Brosnan", "Lee Tamahori")
        );
    }

    @Override
    public Iterable<Movie> getAvailableMovies() {
        return movies;
    }

    @Override
    public Movie getMovieById(long id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Movie getMovieByName(String name) {
        return movies.stream()
                .filter(movie -> Objects.equals(movie.getName(), name))
                .findFirst()
                .orElse(null);
    }
}
