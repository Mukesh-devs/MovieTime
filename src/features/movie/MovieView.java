package features.movie;

import dto.Movie;
import dto.ShowTime;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class MovieView {

    private final MovieModel model;

    public MovieView() {
        model = new MovieModel(this);
    }

    public void allMoviesView() {
        List<Movie> movies = new ArrayList<>(model.allMoviesModel());
        String showTimes;
        for ( Movie movie : movies) {
            showTimes = model.showtimesByMovieId(movie.getId());
            Util.message("\nMovie ID : " + movie.getId() +
                    "\nMovie Title : " + movie.getTitle() +
                    "\nMovie Genre : " + movie.getGenre() +
                    "\nMovie Duration : " + movie.getDurationMins() +
                    "\nMovie Showtime : " + showTimes);
        }
    }


}
