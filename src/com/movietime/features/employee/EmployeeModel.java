package com.movietime.features.employee;

import com.movietime.db.EmployeeDb;
import com.movietime.db.MovieDb;
import com.movietime.dto.Employee;
import com.movietime.dto.Movie;

import java.util.List;


public class EmployeeModel {

    private EmployeeView view;

    public EmployeeModel(EmployeeView employeeView) {
        this.view = employeeView;
    }

    void registration(Employee employee) {
        if ( EmployeeDb.getInstance().userAlreadyExist(employee.getUserName()) ) {
            view.registrationFailure();
        }
        else {
            EmployeeDb.getInstance().save(employee);
            view.registrationSuccess();
        }
    }

    public void employeeRegisterModel(String name, String userName,  String email, String pass) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setUserName(userName);
        employee.setPassword(pass);
        employee.setEmail(email);
        registration(employee);


    }

    public void employeeLoginModel(String userName, String pass) {
        if (EmployeeDb.getInstance().validate(userName, pass)) {
            view.loginSuccess();
        } else {
            view.loginFailure();
        }
    }

    public void addMovieModel(int movieId, String title, String genre, String description, int duration) {
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDescription(description);
        movie.setDurationMins(duration);

        if ( MovieDb.getInstance().isMovieIdExists(movieId)) {
            view.addMovieFailure();
        }
        else {
            MovieDb.getInstance().addMovie(movie);
            view.addMovieSuccess();
        }
    }

    public List<Movie> viewAllMoviesModel() {
        return MovieDb.getInstance().getAllMovies();
    }

    public void removeMovieModel(int movieId) {
        if ( MovieDb.getInstance().removeMovie(movieId)) {
            view.removeMovieSuccess();
        }
        else {
            view.removeMovieFailure();
        }
    }
}
