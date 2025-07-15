package com.movietime.features.movie;

import com.movietime.db.MovieDb;
import com.movietime.dto.Movie;
import com.movietime.dto.ShowTime;
import com.movietime.dao.MovieDao;

import java.util.List;
import java.util.Scanner;

public class MovieModel {

    private final MovieView view;
    private MovieDao movieDao;

    MovieModel(MovieView movieView) {
        this.view = movieView;
        this.movieDao = new MovieDao();
    }

    public List<Movie> allMoviesModel() {
        return movieDao.getAllMovies();
    }

    public String showtimesByMovieId(int movieId) {
        StringBuilder sb = new StringBuilder();
        List<ShowTime> showTimes = movieDao.getShowtimesByMovieId(movieId);
        if ( showTimes.isEmpty()) {
            return "No showTimes available.";
        }
        for ( ShowTime showTime : showTimes) {
            if ( showTime.getMovieId() == movieId) {
                sb.append(showTime.getShowtimeTime());
            }
            sb.append("\n Showtime ID : ").append(showTime.getShowtimeId())
                    .append(", Date : ").append(showTime.getShowtimeDate())
                    .append(", Time : ").append(showTime.getShowtimeTime())
                    .append(", Screen ID : ").append(showTime.getScreenId())
                    .append(", Price :").append(showTime.getTicketPrice())
                    .append(", Available Seats : ").append(showTime.getAvailableSeats());
        }
        return sb.toString();
    }
}
