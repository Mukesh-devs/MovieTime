package com.movietime.features.theatre;

import com.movietime.dao.TheatreDao;
import com.movietime.dto.Screen;
import com.movietime.dto.Theatre;

public class TheatreModel {
    private final TheatreView view;
    private final TheatreDao theatreDao;

    public TheatreModel(TheatreView theatreView) {
        this.view = theatreView;
        this.theatreDao = new TheatreDao();
    }

    public void addTheatre(String name, String location) {
        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setLocation(location);
        if (theatreDao.addTheatre(theatre)) {
            view.addTheatreSuccess();
        }
        else {
            view.addTheatreFailure();
        }
    }
    public void addScreen(int theatreId, String screenName, int capacity) {
        Screen screen = new Screen();
        screen.setTheatreId(theatreId);
        screen.setScreenName(screenName);
        screen.setSeatingCapacity(capacity);

        if (theatreDao.addScreen(screen)) {
            view.addScreenSuccess();
        }
        else {
            view.addScreenFailure();
        }
    }
}
