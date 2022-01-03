package com.example.labcdrawer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppData implements Serializable {

    private ArrayList<LocationItem> favouriteStations;
    private ArrayList<Trip> favouriteTrips;
    private MainActivity mainActivity;
    private RealTimeSearchFragment realTimeSearchFragment;
    private String lastUpdatedRealTime;
    private TransportMode realTimeTransportMode;
    private StationRealTimeActivity stationRealTimeActivity;

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

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setRealTimeSearchFragment(RealTimeSearchFragment realTimeSearchFragment) {
        this.realTimeSearchFragment = realTimeSearchFragment;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public RealTimeSearchFragment getRealTimeSearchFragment() {
        return realTimeSearchFragment;
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

    public StationRealTimeActivity getStationRealTimeActivity() {
        return stationRealTimeActivity;
    }

    public void setStationRealTimeActivity(StationRealTimeActivity stationRealTimeActivity) {
        this.stationRealTimeActivity = stationRealTimeActivity;
    }
}
