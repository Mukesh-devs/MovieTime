package com.movietime.features.ticketbooking;

import com.movietime.db.BookingDb;
import com.movietime.db.MovieDb;
import com.movietime.dto.Booking;
import com.movietime.dto.Movie;
import com.movietime.dto.ShowTime;
import com.movietime.dao.Bookingdao;
import com.movietime.dao.MovieDao;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TicketBookingModel {

    private final TicketBookingView view;
    private MovieDao movieDao;
    private Bookingdao bookingDao;

    public TicketBookingModel(TicketBookingView ticketBookingView) {
        this.view = ticketBookingView;
        this.bookingDao = new Bookingdao();
        this.movieDao = new MovieDao();
    }


    public boolean ticketBookingModel(int movieId, int showtimeId, int userId, int numberOfTickets) {

        ShowTime selectedShowtime = movieDao.getShowtimeById(showtimeId);

        if ( selectedShowtime == null) {
            view.invalidShowtimeError();
            return false;
        }

        if ( selectedShowtime.getAvailableSeats() < numberOfTickets ) {
            view.notEnoughSeatsError(selectedShowtime.getAvailableSeats());
            return false;
        }

        double totalAmount = (double) numberOfTickets * selectedShowtime.getTicketPrice();

        Booking newBooking = new Booking();

        newBooking.setMovieId(movieId);
        newBooking.setShowtimeId(showtimeId);
        newBooking.setUserId(userId);
        newBooking.setNumberOfTickets(numberOfTickets);
        newBooking.setBookingDate(LocalDate.now().toString());
        newBooking.setTotalAmount(totalAmount);

        boolean bookingAdded = bookingDao.addBooking(newBooking);

        if ( bookingAdded) {
            selectedShowtime.setAvailableSeats(selectedShowtime.getAvailableSeats() - numberOfTickets);
            movieDao.updateShowtime(selectedShowtime);
            return true;
        }
        else {
            return false;
        }
    }

    public List<Booking> getUpcomingBookings(int userId) {
        List<Booking> userBookings = bookingDao.getBookingsByUserId(userId);
        LocalDateTime now = LocalDateTime.now();
        List<Booking> upcoming = new ArrayList<>();

        for ( Booking booking : userBookings) {
            ShowTime showTime = movieDao.getShowtimeById(booking.getShowtimeId());
            if ( showTime != null) {
                try {
                    LocalDate showtimeDate = LocalDate.parse(showTime.getShowtimeDate());
                    LocalTime showtimeTime = LocalTime.parse(showTime.getShowtimeTime());
                    LocalDateTime showtimeDateTime = LocalDateTime.of(showtimeDate, showtimeTime);
                    if ( showtimeDateTime.isAfter(now)) {
                        upcoming.add(booking);
                    }
                }
                catch (DateTimeParseException e) {
                    view.ErrorParsingDateTime(booking.getBookingId(),e.getMessage());
                }
            }
        }
        return upcoming;
    }

    public List<Booking> getPastBookings(int userId) {
        List<Booking> userBookings = bookingDao.getBookingsByUserId(userId);
        LocalDateTime now = LocalDateTime.now();
        List<Booking> past = new ArrayList<>();

        for ( Booking booking : userBookings) {
            ShowTime showTime = movieDao.getShowtimeById(booking.getShowtimeId());
            if ( showTime != null) {
                try {
                    LocalDate showtimeDate = LocalDate.parse(showTime.getShowtimeDate());
                    LocalTime showtimeTime = LocalTime.parse(showTime.getShowtimeTime());
                    LocalDateTime showtimeDateTime = LocalDateTime.of(showtimeDate, showtimeTime);
                    if ( showtimeDateTime.isBefore(now)) {
                        past.add(booking);
                    }
                }
                catch (DateTimeParseException e) {
                    view.ErrorParsingDateTime(booking.getBookingId(),e.getMessage());
                }
            }
        }
        return past;
    }

    public List<ShowTime> viewAllShowtimeModel() {
        return movieDao.getAllShowtimes();
    }

    public List<Movie> viewAllMoviesModel() {
        return movieDao.getAllMovies();
    }

    public boolean isMovieIdExists(int movieId) {
        return movieDao.isMovieIdExists(movieId);
    }

    public List<ShowTime> getShowtimesByMovieId(int movieId) {
        return movieDao.getShowtimesByMovieId(movieId);
    }

    public Movie getMovieById(int movieId) {
        return movieDao.getMovieById(movieId);
    }

    public ShowTime getShowtimeById(int showtimeId) {
        return movieDao.getShowtimeById(showtimeId);
    }
}
