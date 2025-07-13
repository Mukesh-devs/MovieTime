package com.movietime.features.employee;

import com.movietime.db.EmployeeDb;
import com.movietime.db.MovieDb;
import com.movietime.dto.Employee;
import com.movietime.dto.Movie;
import com.movietime.dao.EmployeeDao;
import com.movietime.dao.MovieDao;

import java.util.List;


public class EmployeeModel {

    private EmployeeView view;
    private EmployeeDao employeeDao;
    private MovieDao movieDao;

    public EmployeeModel(EmployeeView employeeView) {
        this.view = employeeView;
        this.employeeDao = new EmployeeDao();
        this.movieDao = new MovieDao();
    }

    void registration(Employee employee) {
        if ( employeeDao.userAlreadyExist(employee.getUserName()) ) {
            view.registrationFailure();
        }
        else {
            employeeDao.save(employee);
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
        if (employeeDao.validate(userName, pass)) {
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

        if ( movieDao.isMovieIdExists(movieId)) {
            view.addMovieFailure();
        }
        else {
            movieDao.addMovie(movie);
            view.addMovieSuccess();
        }
    }

    public List<Movie> viewAllMoviesModel() {
        return movieDao.getAllMovies();
    }

    public void removeMovieModel(int movieId) {
        if ( movieDao.removeMovie(movieId)) {
            view.removeMovieSuccess();
        }
        else {
            view.removeMovieFailure();
        }
    }
}
