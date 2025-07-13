package com.movietime.dao;

import com.movietime.db.DatabaseConnection;
import com.movietime.dto.Booking;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bookingdao {
    private final Connection connection;

    public Bookingdao() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean addBooking(Booking booking) {
        String query = "insert into bookings (bookingId, movieId, showtimeId, userId, numberOfTickets,bookingDate, totalAmount) values (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, booking.getBookingId());
            ps.setInt(2, booking.getMovieId());
            ps.setInt(3, booking.getShowtimeId());
            ps.setInt(4, booking.getUserId());
            ps.setInt(5, booking.getNumberOfTickets());
            ps.setString(6, booking.getBookingDate());
            ps.setDouble(7, booking.getTotalAmount());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Booking getBookingById(int bookingId) {
        String query = "select * from bookings where bookingId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    return mapResultSetToBooking(rs);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> userBookings = new ArrayList<>();
        String query = "select * from bookings where userId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userBookings.add(mapResultSetToBooking(rs));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userBookings;
    }

    public List<Booking> getAllBookings() {
        List<Booking> allBookings = new ArrayList<>();
        String query = "select * from bookings";
        try ( PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                allBookings.add(mapResultSetToBooking(rs));
            }
        }
        catch ( SQLException e) {
            e.printStackTrace();
        }
        return allBookings;
    }

    public boolean removeBooking(int bookingId) {
        String query = "delete from bookings where bookingId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,bookingId);
            int rowsAffacted = ps.executeUpdate();
            return rowsAffacted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setMovieId(rs.getInt("movieId"));
        booking.setShowtimeId(rs.getInt("showtimeId"));
        booking.setUserId(rs.getInt("userId"));
        booking.setNumberOfTickets(rs.getInt("numberOfTickets"));
        booking.setBookingDate(rs.getString("bookingDate"));
        booking.setTotalAmount(rs.getDouble("totalAmount"));
        return booking;
    }
}
