package com.movietime.dto;

public class Booking {
//    private static int bookingIdCounter = 1000;
    private int bookingId;
    private int movieId;
    private int showtimeId;
    private int userId;
    private int numberOfTickets;
    private String bookingDate;
    private double totalAmount;

    public Booking() {
//        this.bookingId = bookingIdCounter++;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfSeats) {
        this.numberOfTickets = numberOfSeats;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
