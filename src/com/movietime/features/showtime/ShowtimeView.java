package com.movietime.features.showtime;

import com.movietime.dto.Movie;
import com.movietime.dto.ShowTime;
import com.movietime.features.employee.EmployeeView;
import com.movietime.util.Util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeView {

    private final ShowtimeModel model;

    public ShowtimeView() {
        model = new ShowtimeModel(this);
    }

    public void init() {
        showtimeView();
    }
    
    public void showtimeView() {
        Util.message("\n------Manage Showtimes------");
        Util.prompt("\n1. Add showtime" +
                "\n2. Remove showtime" +
                "\n3. Show All showtime" +
                "\n4. back to employee menu" +
                "\n5. Logout\n");
        int choice = Util.choice();
        
        switch (choice) {
            case 1 -> {
                addShowtimetoMovieView();
            }
            case 2 -> {
                removeShowtimeMovieView();
            }
            case 3 -> {
                showAllShowtimeMovieView();
            }
            case 4 -> {
                new EmployeeView().showMenu();
            }
            case 5 -> {
                Util.message("Logging Out...");
                new EmployeeView().init();
            }
            default -> {
                Util.printError("Invalid Choice. Please select again.");
            }
        }
    }

    private void showAllShowtimeMovieView() {
        showAllShowtimeView();
        showtimeView();
    }
    private void showAllShowtimeView() {
        List<ShowTime> showtime = new ArrayList<>(model.viewAllShowtimeModel());
        for ( ShowTime showTime : showtime) {
            Util.message("\nMovie ID : " + showTime.getMovieId() +
                    "\nShowtime ID : " + showTime.getShowtimeId() +
                    "\nTotal Seats : " + showTime.getTotalSeats() +
                    "\nTicket Price : " + showTime.getTicketPrice());
        }
    }

    private void removeShowtimeMovieView() {
        showAllShowtimeView();
        int showtimeId = getShowtimeId();
        if ( model.removeShowtimeModel(showtimeId) ) {
            Util.printSuccess("Showtime removed Successfully..");
        }
        else {
            Util.printError("showtime remove failed" +
                    "\n please try again with correct credintials..");
        }
        showtimeView();
    }

    private int getShowtimeId() {
        Util.prompt("Enter the Showtime ID to remove showtime : ");
        return Util.readInt();
    }

    void addShowtimetoMovieView() {
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
        int movieId = getMovieId();
        while (!model.isMovieIdExists(movieId)) {
            movieId = getMovieId();
        }
        String showtimeTime = getShowtimeTime();
        String showtimeDate = getShowtimeDate();
        String screenNumber = getScreenNumber();
        int totalSeats = getTotalSeats();
        int ticketPrice = getTicketPrice();

        model.addShowtimetoMovieModel(movieId,showtimeDate,showtimeTime, screenNumber, totalSeats, ticketPrice);
    }

    private int getTicketPrice() {
        Util.prompt("Enter the Ticket Price : ");
        return Util.readInt();
    }

    private int getTotalSeats() {
        Util.prompt("Enter the Total Seats : ");
        return Util.readInt();
    }

    private String getScreenNumber() {
        Util.prompt("Enter the Screen Number : ");
        return Util.readLine();
    }

    private String getShowtimeTime() {
        String timeInput;
        while (true) {
            Util.prompt("Enter the Showtime Time (HH:MM): ");
            timeInput = Util.readLine();
            try {
                LocalTime.parse(timeInput);
                break;
            }
            catch (DateTimeParseException e) {
                Util.printError("Invalid time format. Please use HH:MM ( eg. 09:00 or 14:30 ). ");
            }
        }
        return timeInput;
    }

    private String getShowtimeDate() {
        String dateInput;
        while (true) {
            Util.prompt("Enter the Showtime Date (YYYY-MM-DD) : ");
            dateInput = Util.readLine();
            try {
                LocalDate.parse(dateInput);
                break;
            }
            catch (DateTimeParseException e) {
                Util.printError("Invalid date format. Please use YYYY-MM-DD (eg. 2025-06-24). ");
            }
        }
        return dateInput;
    }

    private int getMovieId() {
        Util.prompt("Enter the Movie Id : ");
        return Util.readInt();
    }


    public void addShowtimeSuccess() {
        Util.printSuccess("Showtime added successfully..");
        showtimeView();
    }

    public void addShowtimeFailure() {
        Util.printError("showtime not added" +
                "\n please try again");
        showtimeView();
    }
}
