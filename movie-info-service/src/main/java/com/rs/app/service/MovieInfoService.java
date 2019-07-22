package com.rs.app.service;

import com.rs.app.model.Movie;

public interface MovieInfoService {
    Movie getMovie(long id);
    Movie getMovie(String name);
}
