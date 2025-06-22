package db;

import dto.Movie;
import dto.ShowTime;
import features.showtime.ShowtimeView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieDb {

    private static MovieDb instance;

    private final List<Movie> movies = new ArrayList<>();
    private final List<ShowTime> showtimes = new ArrayList<>();

    private MovieDb() {
    }

    public static MovieDb getInstance() {
        if ( instance == null) {
            instance = new MovieDb();
        }
        return instance;
    }

    public boolean movieIdExist(int movieId) {
        for ( Movie movie : movies) {
            if ( movie.getId() == movieId) {
                return true;
            }
        }
        return false;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addShowtime(ShowTime showTime) {
        showtimes.add(showTime);
    }
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    public List<ShowTime> getAllShowtimes() {
        return new ArrayList<>(showtimes);
    }
    public Movie getMovieById(int id) {
        for ( Movie movie : movies) {
            if ( movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public List<ShowTime> getShowtimesByMovieId(int movieId) {
        List<ShowTime> movieShowtimes = new ArrayList<>();
        for ( ShowTime showtime : showtimes) {
            if ( showtime.getMovieId() == movieId) {
                movieShowtimes.add(showtime);
            }
        }
        return movieShowtimes;
    }

    public boolean removeMovie(int movieId) {
        boolean movieRemoved = false;
        Iterator<Movie> movieIterator = movies.iterator();
        while ( movieIterator.hasNext()) {
            Movie movie = movieIterator.next();
            if ( movie.getId() == movieId) {
                movieIterator.remove();
                movieRemoved = true;
                break;
            }
        }

        if ( movieRemoved) {
            Iterator<ShowTime> showTimeIterator = showtimes.iterator();
            while (showTimeIterator.hasNext()) {
                ShowTime showTime = showTimeIterator.next();
                if ( showTime.getMovieId() == movieId) {
                    showTimeIterator.remove();
                }
            }
        }
        return movieRemoved;
    }

    public boolean isMovieIdExists(int movieId) {
        for ( Movie movie : movies) {
            if ( movie.getId() == movieId) {
                return true;
            }
        }
        return false;
    }

    public ShowTime getShowtimeById(int showtimeId) {
        for ( ShowTime showTime : showtimes) {
            if( showTime.getShowtimeId() == showtimeId){
                return showTime;
            }
        }
        return null;
    }
    public void updateShowtime(ShowTime updatedShowtime) {
        for ( int i = 0; i < showtimes.size(); i++ ) {
            if ( showtimes.get(i).getShowtimeId() == updatedShowtime.getShowtimeId())
                showtimes.set(i,updatedShowtime);
                return;
        }
    }
}
