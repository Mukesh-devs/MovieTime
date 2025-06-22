package dto;

public class ShowTime {
    private static int showtimeIdCounter = 1;
    private int showtimeId;
    private int movieId;
    private String showtimeDate;
    private String showtimeTime;
    private String screenNumber;
    private int totalSeats;
    private int availableSeats;
    private int ticketPrice;

    public ShowTime() {
        this.showtimeId = showtimeIdCounter++;
    }
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(String showtimeDate) {
        this.showtimeDate = showtimeDate;
    }

    public String getShowtimeTime() {
        return showtimeTime;
    }

    public void setShowtimeTime(String showtimeTime) {
        this.showtimeTime = showtimeTime;
    }

    public String getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(String screenNumber) {
        this.screenNumber = screenNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
