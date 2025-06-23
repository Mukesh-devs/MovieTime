package com.movietime.features.movie;

import com.movietime.db.MovieDb;
import com.movietime.dto.Movie;
import com.movietime.dto.ShowTime;

import java.util.List;

public class MovieModel {

    private final MovieView view;

    MovieModel(MovieView movieView) {
        this.view = movieView;
    }

    public List<Movie> allMoviesModel() {
        return MovieDb.getInstance().getAllMovies();
    }

    public String showtimesByMovieId(int movieId) {
        StringBuilder sb = new StringBuilder();
        for ( ShowTime showTime : MovieDb.getInstance().getShowtimesByMovieId(movieId)) {
            if ( showTime.getMovieId() == movieId) {
                sb.append(showTime.getShowtimeTime());
            }
        }
        return sb.toString();
    }
}
