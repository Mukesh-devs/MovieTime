package com.movietime.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.movietime.db.DatabaseConnection;
import com.movietime.dto.Screen;
import com.movietime.dto.Theatre;

public class TheatreDao {

    private final Connection connection;

    public TheatreDao() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean addTheatre(Theatre theatre) {
        String query = "insert into theatres (name, location) values (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,theatre.getName());
            ps.setString(2,theatre.getLocation());
            int rowsAffected = ps.executeUpdate();
            if ( rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if ( generatedKeys.next()) {
                        theatre.setTheatreId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addScreen(Screen screen) {
        String query = "insert into screens ( screenName, theatreId, seatingCapacity) values (?,?,?)";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,screen.getScreenName());
            ps.setInt(2,screen.getTheatreId());
            ps.setInt(3,screen.getSeatingCapacity());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Theatre> getAllTheatres() {
        String query = "select * from theatres";
        List<Theatre> theatres = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(query);
              ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Theatre theatre = new Theatre();
                theatre.setTheatreId(rs.getInt("theatreId"));
                theatre.setName(rs.getString("name"));
                theatre.setLocation(rs.getString("location"));
                theatres.add(theatre);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return theatres;
    }

    public List<Screen> getScreenByTheatreId(int theatreId) {
        List<Screen> screens = new ArrayList<>();
        String query = "select * from screens where theatreId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,theatreId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Screen screen = new Screen();
                    screen.setScreenId(rs.getInt("screenId"));
                    screen.setScreenName(rs.getString("screenName"));
                    screen.setTheatreId(rs.getInt("theatreId"));
                    screen.setSeatingCapacity(rs.getInt("seatingCapacity"));
                    screens.add(screen);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return screens;
    }

    public Screen getScreenById(int screenId) {
        String query = "select * from screens where screenId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,screenId);
            try ( ResultSet rs = ps.executeQuery()) {
                if ( rs.next()) {
                    Screen screen = new Screen();
                    screen.setScreenId(rs.getInt("screenId"));
                    screen.setScreenName(rs.getString("screenName"));
                    screen.setTheatreId(rs.getInt("theatreId"));
                    screen.setSeatingCapacity(rs.getInt("seatingCapacity"));
                    return screen;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Theatre> getTheatresByLocation(String location) {
        List<Theatre> theatres = new ArrayList<>();
        String query = "select * from theatres where location like ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,"%" + location + "%");
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Theatre theatre = new Theatre();
                    theatre.setTheatreId(rs.getInt("theatreId"));
                    theatre.setName(rs.getString("name"));
                    theatre.setLocation(rs.getString("location"));
                    theatres.add(theatre);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return theatres;
    }
}
