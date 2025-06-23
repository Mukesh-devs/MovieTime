package com.movietime;

import com.movietime.features.employee.EmployeeView;
import com.movietime.features.user.UserView;
import com.movietime.util.Util;

public class MovieTimeBookingSystem {
    public void init() {
        askRegisterLogin();
    }
    void askRegisterLogin() {
        while (true) {
            Util.prompt("Welcome to MovieTime Booking App\n" +
                    "1. Employee \n" +
                    "2. User\n" +
                    "3. Exit\n");
            int homeChoice = Util.choice();
            if ( homeChoice == Integer.MIN_VALUE ) return;
            switch (homeChoice) {
                case 1 -> {
                    new EmployeeView().init();
                }
                case 2 -> {
                    new UserView().init();
                }
                case 3 -> {
                    Util.message("Thank you for using MovieTime Booking App!");
                    System.exit(0);
                }
                default -> {
                    Util.printError("Enter the Correct the choice");
                }
            }
        }
    }
}
