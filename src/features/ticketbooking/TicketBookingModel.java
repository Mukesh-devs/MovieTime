package features.ticketbooking;

import db.BookingDb;
import db.MovieDb;
import dto.Booking;
import dto.Movie;
import dto.ShowTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TicketBookingModel {

    private final TicketBookingView view;

    public TicketBookingModel(TicketBookingView ticketBookingView) {
        this.view = ticketBookingView;
    }


    public boolean ticketBookingModel(int movieId, int showtimeId, int userId, int numberOfTickets) {

        ShowTime selectedShowtime = MovieDb.getInstance().getShowtimeById(showtimeId);

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

        boolean bookingAdded = BookingDb.getInstance().addBooking(newBooking);

        if ( bookingAdded) {
            selectedShowtime.setAvailableSeats(selectedShowtime.getAvailableSeats() - numberOfTickets);
            return true;
        }
        else {
            return false;
        }
    }

    public List<Booking> getUpcomingBookings(int userId) {
        List<Booking> userBookings = BookingDb.getInstance().getBookingsByUserId(userId);
        LocalDateTime now = LocalDateTime.now();
        List<Booking> upcoming = new ArrayList<>();

        for ( Booking booking : userBookings) {
            ShowTime showTime = MovieDb.getInstance().getShowtimeById(booking.getShowtimeId());
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
        List<Booking> userBookings = BookingDb.getInstance().getBookingsByUserId(userId);
        LocalDateTime now = LocalDateTime.now();
        List<Booking> past = new ArrayList<>();

        for ( Booking booking : userBookings) {
            ShowTime showTime = MovieDb.getInstance().getShowtimeById(booking.getShowtimeId());
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
        return MovieDb.getInstance().getAllShowtimes();
    }

    public List<Movie> viewAllMoviesModel() {
        return MovieDb.getInstance().getAllMovies();
    }
}
