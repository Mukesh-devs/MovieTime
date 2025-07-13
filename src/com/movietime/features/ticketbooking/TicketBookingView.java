package com.movietime.features.ticketbooking;

import com.movietime.db.MovieDb;
import com.movietime.dto.Booking;
import com.movietime.dto.Movie;
import com.movietime.dto.ShowTime;
import com.movietime.features.movie.MovieView;
import com.movietime.features.user.UserView;
import com.movietime.util.Util;
import com.movietime.MovieTimeBookingSystem;

import java.util.List;

public class TicketBookingView {

    private final TicketBookingModel model;

    public TicketBookingView() {
        model = new TicketBookingModel(this);
    }

    public void init() {
        manageTicketsView();
    }

    private void manageTicketsView() {
        while (true) {
            Util.message("---- Manage Tickets ----");
            Util.prompt("1. Book Tickets" +
                    "2. Upcoming Tickets" +
                    "3. Ticket History" +
                    "4. Back Menu" +
                    "5. Logout");
            int choice = Util.choice();
            if ( choice == Integer.MIN_VALUE) return;
            switch (choice ) {
                case 1 -> {
                    ticketBookingView();
                }
                case 2 -> {
                    upcomingTicketsView();
                }
                case 3 -> {
                    ticketHistoryView();
                }
                case 4 -> {
                    new UserView().userMenu();
                    return;
                }
                case 5 -> {
                    Util.loggedInUserId = -1;
                    Util.loggedInUser = null;
                    new MovieTimeBookingSystem().init();
                    return;
                }
                default -> {
                    Util.printError("Invalid Option.");
//                    manageTicketsView();

                }
            }
        }
    }

    private void ticketHistoryView() {
        Util.message("--- Your Past History ----");
        //show past booked ticket History
        if ( Util.loggedInUserId == -1) {
            Util.printError("No user Logged in.");
            return;
        }

        List<Booking> userBookings = model.getPastBookings(Util.loggedInUserId);
        if ( userBookings.isEmpty()) {
            Util.message("No Past Tickets Found.");
        }
        else {
            for (Booking booking : userBookings) {
                Movie movie = model.getMovieById(booking.getMovieId());
                ShowTime showTime = model.getShowtimeById(booking.getShowtimeId());
                if ( movie != null && showTime != null) {
                    Util.message("\nBooking Id" + booking.getBookingId() +
                            "\nMovie Name : " + movie.getTitle() +
                            "\nShow Date : " + showTime.getShowtimeDate() +
                            "\nShow Time : " + showTime.getShowtimeTime() +
                            "\nScreen Number: " + showTime.getScreenNumber() +
                            "\nBooked Seats : " + booking.getNumberOfTickets() +
                            "\nTotal Amount : " + String.format("%.2f",booking.getTotalAmount()));
                }
                else {
                    Util.printError("Could not retrive movie or showtime details for booking ID");
                }
            }
        }
//        manageTicketsView();
    }

    private void upcomingTicketsView() {
        //show booked movie tickets for upcoming date and time
        Util.message("--- Your Upcoming Booking ----");
        //show past booked ticket History
        if ( Util.loggedInUserId == -1) {
            Util.printError("No user Logged in.");
            return;
        }

        List<Booking> userBookings = model.getUpcomingBookings(Util.loggedInUserId);
        if ( userBookings.isEmpty()) {
            Util.message("No upcoming Tickets Found.");
        }
        else {
            for (Booking booking : userBookings) {
                Movie movie = model.getMovieById(booking.getMovieId());
                ShowTime showTime = model.getShowtimeById(booking.getShowtimeId());
                if ( movie != null && showTime != null) {
                    Util.message("\nBooking Id" + booking.getBookingId() +
                            "\nMovie Name : " + movie.getTitle() +
                            "\nShow Date : " + showTime.getShowtimeDate() +
                            "\nShow Time : " + showTime.getShowtimeTime() +
                            "\nScreen Number : " + showTime.getScreenNumber() +
                            "\nBooked Seats : " + booking.getNumberOfTickets() +
                            "\nTotal Amount : " + String.format("%.2f",booking.getTotalAmount()));
                }
                else {
                    Util.printError("Could not retrive movie or showtime details for booking ID");
                }
            }
        }
//        manageTicketsView();
    }

    private void ticketBookingView() {
        Util.message("---- Book Tickets ----");
        new MovieView().allMoviesView();
        Util.prompt("Enter the Movie Id to Book the Ticket : ");
        int movieId = Util.readInt();
        if ( movieId == Integer.MIN_VALUE) return;
        if (!model.isMovieIdExists(movieId)) {
            Util.printError("Movie ID not found. Please enter a valid Movie ID.");
//            ticketBookingView();
            return;
        }
        List<ShowTime> movieShowtimes = model.getShowtimesByMovieId(movieId);
        if ( movieShowtimes.isEmpty()) {
            Util.printError("No showtimes available for this movie.");
//            manageTicketsView();
            return;
        }

        Util.message("\nAvailable Showtimes for Movie ID " + movieId + ":");
        for (ShowTime st : movieShowtimes) {
            Util.message("Showtime ID: " + st.getShowtimeId() +
                    ", Date: " + st.getShowtimeDate() +
                    ", Time: " + st.getShowtimeTime() +
                    ", Screen: " + st.getScreenNumber() +
                    ", Price: $" + String.format("%.2f", (double)st.getTicketPrice()) + // Format price
                    ", Available Seats: " + st.getAvailableSeats());
        }

        Util.prompt("Enter the Showtime ID you want to book : ");
        int showtimeId = Util.readInt();
        if ( showtimeId == Integer.MIN_VALUE) return;
        Util.prompt("Enter the number of tickets : ");
        int numberOfTickets = Util.readInt();
        if ( numberOfTickets == Integer.MIN_VALUE) return;
        if ( Util.loggedInUserId == -1) {
            Util.printError("you must logged in to book tickets.");
//            manageTicketsView();
            return;
        }
        int userId = Util.loggedInUserId;

        boolean success = model.ticketBookingModel(movieId, showtimeId, userId, numberOfTickets);

        if ( success) {
            Util.printSuccess("Tickets Booked Successfully");
        }
        else {
            Util.printError("Ticket booking failed. Please check the details and try again");
        }
//        manageTicketsView();
    }

    public void ErrorParsingDateTime(int bookingId, String message) {
        Util.printError("Error parsing date and time for Booking " + bookingId + " : " + message);
    }

    public void invalidShowtimeError() {
        Util.printError("Invalid Showtime...");
    }

    public void notEnoughSeatsError(int availableSeats) {
        Util.printError("Not Enough Seats" + availableSeats + " seats Available");
    }
}
