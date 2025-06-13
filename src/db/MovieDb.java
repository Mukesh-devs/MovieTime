package db;

import dto.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDb {

    private static MovieDb instance;
    private final List<Movie> movies = new ArrayList<>();

    private MovieDb() {}

    public static MovieDb getInstance() {
        if ( instance == null) {
            instance = new MovieDb();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    public Movie getMovieById(int id) {
        for ( Movie movie : movies) {
            if ( movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
}
