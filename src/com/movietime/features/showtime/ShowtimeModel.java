package com.movietime.features.showtime;

import com.movietime.db.MovieDb;
import com.movietime.dao.MovieDao;
import com.movietime.dto.Movie;
import com.movietime.dto.ShowTime;

import java.util.List;

public class ShowtimeModel {

    private final ShowtimeView view;
    private MovieDao movieDao;

    public ShowtimeModel(ShowtimeView showtimeView) {
        this.view = showtimeView;
        this.movieDao = new MovieDao();
    }

    public boolean isMovieIdExists(int movieId) {
        return movieDao.isMovieIdExists(movieId);
    }

    public void addShowtimetoMovieModel(int movieId, String showtimeDate, String showtimeTime, String screenNumber, int totalSeats, int ticketPrice) {
        ShowTime showTime = new ShowTime();
        showTime.setMovieId(movieId);
        showTime.setShowtimeDate(showtimeDate);
        showTime.setShowtimeTime(showtimeTime);
        showTime.setScreenNumber(screenNumber);
        showTime.setTotalSeats(totalSeats);
        showTime.setAvailableSeats(totalSeats);
        showTime.setTicketPrice(ticketPrice);

        if ( movieDao.addShowtime(showTime) ) {
            view.addShowtimeSuccess();
        }
        else {
            view.addShowtimeFailure();
        }
    }

    public List<ShowTime> viewAllShowtimeModel() {
        return movieDao.getAllShowtimes();
    }

    public List<Movie> viewAllMoviesModel() {
        return movieDao.getAllMovies();
    }

    public boolean removeShowtimeModel(int showtimeId) {
        return movieDao.removeShowtime(showtimeId);
    }
}
