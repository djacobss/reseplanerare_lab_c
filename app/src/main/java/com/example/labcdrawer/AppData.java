package com.example.labcdrawer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppData implements Serializable {

    private ArrayList<LocationItem> favouriteStations;
    private ArrayList<Trip> favouriteTrips;
    private String lastUpdatedRealTime, lastSearchString;
    private TransportMode realTimeTransportMode;

    public AppData() {
        favouriteStations = new ArrayList<>();
        favouriteTrips = new ArrayList<>();
        realTimeTransportMode = TransportMode.BUS;
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
        for (LocationItem item : favouriteStations) {
            if (item.getPlaceName().equals(station.getPlaceName())) {
                return false;
            }
        }
        favouriteStations.add(station);
        return true;
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
        for (LocationItem item : favouriteStations) {
            if (item.getPlaceName().equals(station.getPlaceName())) {
                if (favouriteStations.remove(station)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeFavouriteTrip(Trip trip) {
        if (favouriteTrips.contains(trip)) {
            favouriteTrips.remove(trip);
            return true;
        } else {
            return false;
        }
    }

    public String getLastUpdatedRealTime() {
        return lastUpdatedRealTime;
    }

    public void setLastUpdatedRealTime(String lastUpdatedRealTime) {
        this.lastUpdatedRealTime = lastUpdatedRealTime;
    }

    public TransportMode getRealTimeTransportMode() {
        return realTimeTransportMode;
    }

    public void setRealTimeTransportMode(TransportMode realTimeTransportMode) {
        this.realTimeTransportMode = realTimeTransportMode;
    }

    public String getLastSearchString() {
        return lastSearchString;
    }

    public void setLastSearchString(String leastSearchString) {
        this.lastSearchString = leastSearchString;
    }
}
