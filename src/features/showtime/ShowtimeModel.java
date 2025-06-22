package features.showtime;

import db.MovieDb;
import dto.Movie;
import dto.ShowTime;

import java.util.List;

public class ShowtimeModel {

    private final ShowtimeView view;

    public ShowtimeModel(ShowtimeView showtimeView) {
        this.view = showtimeView;
    }

    public boolean isMovieIdExists(int movieId) {
        return MovieDb.getInstance().isMovieIdExists(movieId);
    }

    public void addShowtimetoMovieModel(int movieId, String showtimeDate, String showtimeTime, String screenNumber, int totalSeats, int ticketPrice) {
        ShowTime showTime = new ShowTime();
        showTime.setMovieId(movieId);
        showTime.setShowtimeDate(showtimeDate);
        showTime.setShowtimeTime(showtimeTime);
        showTime.setScreenNumber(screenNumber);
        showTime.setTotalSeats(totalSeats);
        showTime.setTicketPrice(ticketPrice);

        MovieDb.getInstance().addShowtime(showTime);
    }

    public List<ShowTime> viewAllShowtimeModel() {
        return MovieDb.getInstance().getAllShowtimes();
    }

    public List<Movie> viewAllMoviesModel() {
        return MovieDb.getInstance().getAllMovies();
    }

}
