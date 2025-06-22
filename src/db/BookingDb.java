package db;

import dto.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDb {
    private static BookingDb instance;
    private final Map<Integer, Booking> bookings;

    private BookingDb() {
        bookings = new HashMap<>();
    }
    public static BookingDb getInstance() {
        if ( instance == null ) {
            instance = new BookingDb();
            return instance;
        }
        return instance;
    }

    public Booking getBookingById(int bookingId) {
        return bookings.get(bookingId);
    }
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> userBookings = new ArrayList<>();

        for ( Booking booking : bookings.values()) {
            if ( booking.getUserId() == userId){
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public boolean addBooking(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
        return true;
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }

    public boolean removeBooking(int bookingId) {
        if ( bookings.containsKey(bookingId)) {
            bookings.remove(bookingId);
            return true;
        }
        return false;
    }
}
