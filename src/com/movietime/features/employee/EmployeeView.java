package com.movietime.features.employee;

import com.movietime.dto.Employee;
import com.movietime.dto.Movie;
import com.movietime.features.showtime.ShowtimeView;
import com.movietime.util.Util;
import com.movietime.MovieTimeBookingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeView {
    final Employee employee = new Employee();
    private final EmployeeModel model;
    Scanner scanner = new Scanner(System.in);
    public EmployeeView() {
        model = new EmployeeModel(this);
    }

    public void init() {
        while (true) {
            Util.prompt("\nEMPLOYEE PORTAL" +
                    "\n1. Register" +
                    "\n2. Login" +
                    "\n3. change user" +
                    "\n4. Exit");
            int menuChoice = Util.choice();
            switch (menuChoice) {
                case 1 -> employeeRegisterView();
                case 2 -> employeeLoginView();
                case 3 -> new MovieTimeBookingSystem().init();
                case 4 -> System.exit(0);
                default -> Util.printError("Enter a valid choice.");
            }
        }
    }


    public String getName() {
        String name;
        while (true) {
            Util.prompt("Enter your Full Name : ");
            name = Util.readLine().trim();
            if ( name.matches("[a-zA-Z ]{3,}")) {
                return name;
            }
            else {
                Util.printError("Name must be minimum of 3 letters contains only Alphabets");
            }
        }
    }

    public String getUserName() {
        String username;
        while (true) {
            Util.prompt("Enter your UserName : ");
            username = Util.readLine().trim();
            if ( username.matches("[a-zA-Z0-9 ]{3,}")) {
                return username;
            }
            else {
                Util.printError("UserName must be minimum of 3 letters contains only alphaNumeric.");
            }
        }
    }

    public String getPassword() {
        String pass;
        while (true) {
            Util.prompt("Enter the Password : ");
            pass = Util.readLine().trim();
            if ( pass.matches("[a-zA-Z0-9@#!$%^&*()_+={};:'<,>.?/]{5,}")) {
                return pass;
            }
            else {
                Util.printError("Password must be minimum of 5 letters. Please try again");
            }
        }
    }

    public String getEmail() {
        String email;
        while (true) {
            Util.prompt("Enter the Email : ");
            email = Util.readLine().trim();
            if ( email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }
            else {
                Util.printError("Email is not valid. Please try again");
            }
        }
    }

    void employeeRegisterView() {
        String name = getName();
        String userName = getUserName();
        String email = getEmail();
        String pass;
        while (true) {
            pass = getPassword();
            String repass = getRePassword();
            if (pass.equals(repass)) {
                break;
            } else {
                Util.printError("Password and Re-password do not match. Try again.");
            }
        }
        model.employeeRegisterModel(name,userName,email,pass);
    }

    private String getRePassword() {
        String repass;
        while (true) {
            Util.prompt("Enter the RePassword : ");
            repass = Util.readLine().trim();
            if ( repass.matches("[a-zA-Z0-9@#!$%^&*()_+={};:'<,>.?/]{5,}")) {
                return repass;
            }
            else {
                Util.printError("RePassword must be minimum of 5 letters. Please try again");
            }
        }
    }


    void employeeLoginView() {
        String username = getUserName();
        String password = getPassword();
        model.employeeLoginModel(username, password);
    }

    public void loginSuccess() {
        Util.printSuccess("Employee login Successfull");
        showMenu();
    }
    public void showMenu() {
        while (true) {
            Util.prompt("\n --- Employee Home ---" +
                    "\n1. Manage Movies" +
                    "\n2. Manage Showtime" +
                    "\n3. Logout");
            int choice = Util.choice();
            switch (choice) {
                case 1 -> movieView();
                case 2 -> new ShowtimeView().init();
                case 3 -> {
                    Util.message("Logging Out...");
                    init();
                }
                default -> Util.printError("Invalid Choice. Please Select Again");
            }
        }
    }

    private void movieView() {
        while (true) {
            Util.prompt("\n--- Manage Movies ---" +
                    "\n1. Add Movie" +
                    "\n2. Remove Movie" +
                    "\n3. View All Movies" +
                    "\n4. Logout");
            int choice = Util.choice();
            switch (choice) {
                case 1 -> addMovieView();
                case 2 -> removeMovieView();
                case 3 -> viewAllMovies();
                case 4 -> {
                    Util.message("Logging Out...");
                    init();
                }
                default -> Util.printError("Invalid Choice. Please select again");
            }
        }
    }

    private void removeMovieView() {
        viewAllMovies();
        Util.prompt("Enter the MovieId to remove : ");
        int removeMovieChoice = Util.readInt();
        model.removeMovieModel(removeMovieChoice);
    }

    private void viewAllMovies() {
        List<Movie> movies = new ArrayList<>(model.viewAllMoviesModel());
        int index = 1;
        for ( Movie movie : movies) {
            Util.message(index + ". Movie ID \t\t: " + movie.getId() + "\n" +
                        "Movie Title \t\t: " + movie.getTitle() + "\n" +
                    "Movie Genre \t\t: " + movie.getGenre() + "\n" +
                    "Movie Description \t: " + movie.getDescription() + "\n" +
                    "Movie Duration \t\t: " + movie.getDurationMins() + "\n");
            index++;
        }
    }

    private void addMovieView() {
        Util.message("\n---Add new Movie---\n");
        int movieId = getMovieId();
        String title = getMovieTitle();
        String genre = getMovieGenre();
        String decription = getMovieDescription();
        int duration = getMovieDuration();
        model.addMovieModel(movieId, title, genre, decription, duration);
    }

    private String getMovieDescription() {
        Util.prompt("Enter the Description : ");
        return Util.readLine();
    }

    private int getMovieDuration() {
        Util.prompt("Enter the duration of Movie : ");
        return Util.readInt();
    }

    private String getMovieGenre() {
        Util.prompt("Enter Movie Genre : ");
        return Util.readLine();
    }

    private String getMovieTitle() {
        Util.prompt("Enter Movie Title : ");
        return Util.readLine();
    }

    private int getMovieId() {
        Util.prompt("Enter Movie ID : ");
        return Util.readInt();
    }


    public void loginFailure() {
        Util.printError("Login Credintials does not match");
        employeeLoginView();
    }

    public void registrationFailure() {
        Util.printError("Employee Already Exists..");
        employeeRegisterView();
    }

    public void registrationSuccess() {
        Util.printSuccess("Employee Registration Successfull");
//        employeeLoginView();
        init();
    }

    public void addMovieFailure() {
        Util.printError("Movie Id Already Exists...");
        addMovieView();
    }

    public void addMovieSuccess() {
        Util.printSuccess("Movie Added SuccessFull...");
        showMenu();
    }

    public void removeMovieSuccess() {
        Util.printSuccess("Movie Removed Successfull");
//        showMenu();
    }

    public void removeMovieFailure() {
        Util.printError("Movie removed Failed..");
        Util.printError("check the movie credentials");
//        showMenu();
    }
}
