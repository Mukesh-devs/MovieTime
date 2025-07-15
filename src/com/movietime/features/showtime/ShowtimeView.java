package com.movietime.features.showtime;

import com.movietime.dto.Movie;
import com.movietime.dto.Screen;
import com.movietime.dto.ShowTime;
import com.movietime.dto.Theatre;
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
        while (true) {
            Util.message("\n------Manage Showtimes------");
            Util.prompt("\n1. Add showtime" +
                    "\n2. Remove showtime" +
                    "\n3. Show All showtime" +
                    "\n4. back to employee menu" +
                    "\n5. Logout\n");
            int choice = Util.choice();
            if ( choice == Integer.MIN_VALUE) return;
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
                    return;
                }
                case 5 -> {
                    Util.message("Logging Out...");
                    new EmployeeView().init();
                    return;
                }
                default -> {
                    Util.printError("Invalid Choice. Please select again.");
                }
            }
        }
    }

    private void showAllShowtimeMovieView() {
        showAllShowtimeView();
//        showtimeView();
    }
    private void showAllShowtimeView() {
        List<ShowTime> showtime = new ArrayList<>(model.viewAllShowtimeModel());
        if ( showtime.isEmpty()) {
            Util.message("No showtimes to display.");
            return;
        }
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
        if ( showtimeId == Integer.MIN_VALUE) return;
        if ( model.removeShowtimeModel(showtimeId) ) {
            Util.printSuccess("Showtime removed Successfully..");
        }
        else {
            Util.printError("showtime remove failed" +
                    "\n please try again with correct credintials..");
        }
//        showtimeView();
    }

    private int getShowtimeId() {
        Util.prompt("Enter the Showtime ID to remove showtime : ");
        return Util.readInt();
    }

    void addShowtimetoMovieView() {
        List<Movie> movies = new ArrayList<>(model.viewAllMoviesModel());
        if ( movies.isEmpty()) {
            Util.message("No movies available to add showtime.");
            return;
        }
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
        if ( movieId == Integer.MIN_VALUE) return;
        while (!model.isMovieIdExists(movieId)) {
            Util.printError("Movie ID does not exist. Please enter a valid Movie ID.");
            movieId = getMovieId();
            if ( movieId == Integer.MIN_VALUE) return;
        }

        List<Theatre> theatres = model.getAllTheatres();
        if ( theatres.isEmpty()) {
            Util.message("No theatres available. Please add a theatre first.");
            return;
        }
        Util.message("\n--- Select a Theatre ---");
        for ( Theatre theatre : theatres) {
            Util.message("Theatre ID : " + theatre.getTheatreId() + " - " +
                    theatre.getName() + " - " + theatre.getLocation());
        }
        Util.prompt("Enter the Theatre ID : ");
        int theatreId = Util.readInt();
        if ( theatreId == Integer.MIN_VALUE) return;

        List<Screen> screens = model.getScreenByTheatre(theatreId);
        if ( screens.isEmpty()) {
            Util.message("No Screens for this theatre. Please add a screen first.");
            return;
        }
        Util.message("\n--- Select a Screen ---");
        for ( Screen screen : screens) {
            Util.message("Screen ID : " + screen.getScreenId() +
                    " - " + screen.getScreenName() +
                    " (capacity : " + screen.getSeatingCapacity() + " )");
        }

        Util.prompt("Enter the screen ID: ");
        int screenId = Util.readInt();
        if ( screenId == Integer.MIN_VALUE) return;

        String showtimeTime = getShowtimeTime();
        if ( showtimeTime == null) return;
        String showtimeDate = getShowtimeDate();
        if ( showtimeDate == null) return;
//        String screenNumber = getScreenNumber();
//        if ( screenNumber == null) return;
//        int totalSeats = getTotalSeats();
//        if ( totalSeats == Integer.MIN_VALUE) return;
        int ticketPrice = getTicketPrice();
        if ( ticketPrice == Integer.MIN_VALUE) return;

        model.addShowtimeModel(movieId,screenId, showtimeDate,showtimeTime,ticketPrice);
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
            if ( timeInput == null) return null;
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
            if ( dateInput == null) return null;
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
//        showtimeView();
    }

    public void addShowtimeFailure() {
        Util.printError("showtime not added" +
                "\n please try again");
//        showtimeView();
    }
}
