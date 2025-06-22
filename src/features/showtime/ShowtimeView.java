package features.showtime;

import db.MovieDb;
import dto.Movie;
import dto.ShowTime;
import features.employee.EmployeeView;
import util.Util;

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
    
    public void   showtimeView() {
        Util.message("------Manage Showtimes------");
        Util.prompt("1. Add showtime\n" +
                "2. Show All showtime\n" +
                "3. back to employee menu\n" +
                "4. Exit\n");
        int choice = Util.choice();
        
        switch (choice) {
            case 1 -> {
                addShowtimetoMovieView();
            }
            case 2 -> {
                showAllShowtimeMovieView();
            }
            case 3 -> {
                new EmployeeView().showMenu();
            }
            case 4 -> System.exit(0);
        }
    }

    private void showAllShowtimeMovieView() {
        List<ShowTime> showtime = new ArrayList<>(model.viewAllShowtimeModel());
        for ( ShowTime showTime : showtime) {
            Util.message(showTime.getMovieId() +"\n" +
                    showTime.getShowtimeId() + "\n" +
                    showTime.getTotalSeats() + "\n" +
                    showTime.getTicketPrice());
        }
        showtimeView();
    }

    private void removeShowtimeMovieView() {
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
        Util.prompt("Enter the Showtime Time : ");
        return Util.readLine();
    }

    private String getShowtimeDate() {
        Util.prompt("Enter the Showtime Date : ");
        return Util.readLine();
    }

    private int getMovieId() {
        Util.prompt("Enter the Movie Id : ");
        return Util.readInt();
    }
    

}
