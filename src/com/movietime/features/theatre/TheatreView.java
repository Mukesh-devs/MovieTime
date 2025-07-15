package com.movietime.features.theatre;

import com.movietime.dao.TheatreDao;
import com.movietime.util.Util;

public class TheatreView {
    private final TheatreModel model;
    
    public TheatreView() {
        this.model = new TheatreModel(this);
    }

    public void showTheatreManagementMenu() {
        while (true) {
            Util.message("\n--- Manage Theatres & Screens ---");
            Util.prompt("1. Add theatre \n2. Add screen \n3. Back to Admin Menu");
            int choice = Util.choice();
            switch (choice) {
                case 1 -> {
                    addTheatreView();
                }
                case 2 -> {
                    addScreenView();
                }
                case 3 -> {
                    return;
                }
                default -> {
                    Util.printError("Invalid Choice");
                }
            }
        }
    }

    private void addScreenView() {
        Util.prompt("Enter Theatre ID to add screen to: ");
        int theatreId = Util.readInt();
        Util.prompt("Enter the screen Name (e.g. 'screen 1') : ");
        String screenName = Util.readLine();
        Util.prompt("Enter the Seating Capacity : ");
        int capacity = Util.readInt();
        model.addScreen(theatreId, screenName, capacity);
    }

    private void addTheatreView() {
        Util.prompt("Enter Theatre Name : ");
        String name = Util.readLine();
        Util.prompt("Enter Theatre Location : ");
        String location = Util.readLine();
        model.addTheatre(name,location);
    }

    public void addTheatreSuccess() {
        Util.printSuccess("Theatre added Successfully!");
    }

    public void addTheatreFailure() {
        Util.printError("Failed to add Theatre.");
    }

    public void addScreenSuccess() {
        Util.printSuccess("Screen added Successfully!");
    }

    public void addScreenFailure() {
        Util.printError("Failed to add Screen.");
    }
}
