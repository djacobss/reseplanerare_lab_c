package com.example.labcdrawer;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    private List<Station> favouriteStations;
    private List<Trip> favouriteTrips;

    public AppData() {
        favouriteStations = new ArrayList<>();
        favouriteTrips = new ArrayList<>();
    }

    public List<Station> getFavouriteStations() {
        return favouriteStations;
    }

    public void setFavouriteStations(List<Station> favouriteStations) {
        this.favouriteStations = favouriteStations;
    }

    public List<Trip> getFavouriteTrips() {
        return favouriteTrips;
    }

    public void setFavouriteTrips(List<Trip> favouriteTrips) {
        this.favouriteTrips = favouriteTrips;
    }

    public boolean addFavouriteStation(Station station) {
        if (favouriteStations.contains(station)) {
            return false;
        } else {
            favouriteStations.add(station);
            return true;
        }
    }

    public boolean addFavouriteTrip(Trip trip) {
        if (favouriteTrips.contains(trip)) {
            return false;
        } else {
            favouriteTrips.add(trip);
            return true;
        }
    }

    public boolean removeFavouriteStation(Station station) {
        if (favouriteStations.contains(station)) {
            favouriteStations.remove(station);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeFavouriteTrip(Trip trip) {
        if (favouriteTrips.contains(trip)) {
            favouriteTrips.remove(trip);
            return true;
        } else {
            return false;
        }
    }
}
