package com.movietime.features.showtime;

import com.movietime.dao.TheatreDao;
import com.movietime.db.MovieDb;
import com.movietime.dao.MovieDao;
import com.movietime.dto.Movie;
import com.movietime.dto.Screen;
import com.movietime.dto.ShowTime;
import com.movietime.dto.Theatre;

import java.util.List;

public class ShowtimeModel {

    private final ShowtimeView view;
    private MovieDao movieDao;
    private TheatreDao theatreDao;


    public ShowtimeModel(ShowtimeView showtimeView) {
        this.view = showtimeView;
        this.movieDao = new MovieDao();
        this.theatreDao = new TheatreDao();
    }

    public boolean isMovieIdExists(int movieId) {
        return movieDao.isMovieIdExists(movieId);
    }

    public void addShowtimeModel(int movieId, int screenId, String date, String time, int price) {
        Screen selectedScreen = theatreDao.getScreenById(screenId);

        if ( selectedScreen != null) {
            ShowTime newShowtime = new ShowTime();
            newShowtime.setMovieId(movieId);
            newShowtime.setScreenId(screenId);
            newShowtime.setShowtimeDate(date);
            newShowtime.setShowtimeTime(time);
            newShowtime.setTicketPrice(price);

            int capacity = selectedScreen.getSeatingCapacity();
            newShowtime.setTotalSeats(capacity);
            newShowtime.setAvailableSeats(capacity);

            if ( movieDao.addShowtime(newShowtime)) {
                view.addShowtimeSuccess();
            }
            else {
                view.addShowtimeFailure();
            }
        }
        else {
            view.addShowtimeFailure();
        }
    }
    public void addShowtimetoMovieModel(int movieId, String showtimeDate, String showtimeTime, String screenNumber, int totalSeats, int ticketPrice) {
        ShowTime showTime = new ShowTime();
        showTime.setMovieId(movieId);
        showTime.setShowtimeDate(showtimeDate);
        showTime.setShowtimeTime(showtimeTime);
        showTime.setScreenId(Integer.parseInt(screenNumber));
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

    public List<Theatre> getAllTheatres() {
        return theatreDao.getAllTheatres();
    }

    public List<Screen> getScreenByTheatre(int theatreId) {
        return theatreDao.getScreenByTheatreId(theatreId);
    }
}
