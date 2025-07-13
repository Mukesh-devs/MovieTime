package com.movietime.dao;

import com.movietime.db.DatabaseConnection;
import com.movietime.db.MovieDb;
import com.movietime.dto.Movie;
import com.movietime.dto.ShowTime;
import com.sun.jdi.event.StepEvent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    private final Connection connection;

    public MovieDao() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addMovie(Movie movie) {
        String query = "insert into movies (Id,title,genre, description, durationMins) values (?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,movie.getId());
            ps.setString(2, movie.getTitle());
            ps.setString(3,movie.getGenre());
            ps.setString(4,movie.getDescription());
            ps.setInt(5,movie.getDurationMins());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isMovieIdExists(int movieId) {
        String query = "select count(*) from movies where Id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,movieId);
            try (ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Movie getMovieById(int movieId) {
        String query = "select * from movies where Id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,movieId);
            try ( ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    Movie movie = new Movie();
                    movie.setId(rs.getInt("Id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setGenre(rs.getString("genre"));
                    movie.setDescription(rs.getString("description"));
                    movie.setDurationMins(rs.getInt("durationMins"));
                    return movie;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "select * from movies";
        try( PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("Id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDescription(rs.getString("description"));
                movie.setDurationMins(rs.getInt("durationMins"));
                movies.add(movie);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public boolean removeMovie(int movieId) {
        String query = "Delete from movies where Id = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,movieId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addShowtime(ShowTime showTime) {
        String query = "insert into showtimes (movieId, showtimeDate, showtimeTime, screenNumber, totalSeats, availableSeats, ticketPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, showTime.getMovieId());
            ps.setString(2, showTime.getShowtimeDate());
            ps.setString(3, showTime.getShowtimeTime());
            ps.setString(4, showTime.getScreenNumber());
            ps.setInt(5, showTime.getTotalSeats());
            ps.setInt(6, showTime.getAvailableSeats());
            ps.setInt(7, showTime.getTicketPrice());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ShowTime> getShowtimesByMovieId(int movieId) {
        List<ShowTime> movieShowtimes = new ArrayList<>();
        String query = "select * from showtimes where movieId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ShowTime showTime = mapResultSetToShowTime(rs);
                    movieShowtimes.add(showTime);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return movieShowtimes;
    }

    private ShowTime mapResultSetToShowTime(ResultSet rs) throws SQLException {
        ShowTime showTime = new ShowTime();
        showTime.setShowtimeId(rs.getInt("showtimeId"));
        showTime.setMovieId(rs.getInt("movieId"));
        showTime.setShowtimeDate(rs.getString("showtimeDate"));
        showTime.setShowtimeTime(rs.getString("showtimeTime"));
        showTime.setScreenNumber(rs.getString("screenNumber"));
        showTime.setTotalSeats(rs.getInt("totalSeats"));
        showTime.setAvailableSeats(rs.getInt("availableSeats"));
        showTime.setTicketPrice(rs.getInt("ticketPrice"));
        return showTime;
    }

    public boolean removeShowtime(int showtimeId) {
        String query = "delete from showtimes where showtimeId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,showtimeId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateShowtime(ShowTime showTime) {
        String query = "update showtimes set availableSeats = ? where showtimeId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,showTime.getAvailableSeats());
            ps.setInt(2,showTime.getShowtimeId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ShowTime getShowtimeById(int showtimeId) {
        String query = "select * from showtimes where showtimeId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,showtimeId);
            try ( ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    return mapResultSetToShowTime(rs);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ShowTime> getAllShowtimes() {
        List<ShowTime> showtimes = new ArrayList<>();
        String query = "select * from showtimes";
        try( PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                showtimes.add(mapResultSetToShowTime(rs));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return showtimes;
    }
}
