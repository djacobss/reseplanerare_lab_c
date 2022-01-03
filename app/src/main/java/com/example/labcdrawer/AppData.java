package com.example.labcdrawer;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    private ArrayList<LocationItem> favouriteStations;
    private ArrayList<Trip> favouriteTrips;

    public AppData() {
        favouriteStations = new ArrayList<>();
        favouriteTrips = new ArrayList<>();
    }

    public ArrayList<LocationItem> getFavouriteStations() {
        return favouriteStations;
    }

    public void setFavouriteStations(ArrayList<LocationItem> favouriteStations) {
        this.favouriteStations = favouriteStations;
    }

    public List<Trip> getFavouriteTrips() {
        return favouriteTrips;
    }

    public void setFavouriteTrips(ArrayList<Trip> favouriteTrips) {
        this.favouriteTrips = favouriteTrips;
    }

    public boolean addFavouriteStation(LocationItem station) {
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

    public boolean removeFavouriteStation(LocationItem station) {
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
