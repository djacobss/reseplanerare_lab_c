package com.example.labcdrawer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppData implements Serializable {

    private ArrayList<LocationItem> favouriteStations;
    private ArrayList<TripItem> favouriteTripItems;
    private String lastUpdatedRealTime, lastSearchString;
    private TransportMode realTimeTransportMode;

    public AppData() {
        favouriteStations = new ArrayList<>();
        favouriteTripItems = new ArrayList<>();
        realTimeTransportMode = TransportMode.BUS;
    }

    public ArrayList<LocationItem> getFavouriteStations() {
        return favouriteStations;
    }

    public void setFavouriteStations(ArrayList<LocationItem> favouriteStations) {
        this.favouriteStations = favouriteStations;
    }

    public List<TripItem> getFavouriteTrips() {
        return favouriteTripItems;
    }

    public void setFavouriteTrips(ArrayList<TripItem> favouriteTripItems) {
        this.favouriteTripItems = favouriteTripItems;
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

    public boolean addFavouriteTrip(TripItem tripItem) {
        if (favouriteTripItems.contains(tripItem)) {
            return false;
        } else {
            favouriteTripItems.add(tripItem);
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

    public boolean removeFavouriteTrip(TripItem tripItem) {
        if (favouriteTripItems.contains(tripItem)) {
            favouriteTripItems.remove(tripItem);
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
