package com.example.labcdrawer;

import java.util.ArrayList;

public class Model {

    private AppData appData;


    public Model() {

    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }

    public AppData getAppData() {
        return appData;
    }

    public ArrayList<Integer> getFavouriteStationsStrings() {
        ArrayList<Integer> stationStringList = new ArrayList<>();
        if (!appData.getFavouriteStations().isEmpty()) {
            for (Station station : appData.getFavouriteStations()) {
                stationStringList.add(station.getSiteID());
            }
        }
        return stationStringList;
    }

    public ArrayList<Integer> getFavouriteTripsStartStrings() {
        ArrayList<Integer> tripsStartList = new ArrayList<>();
        if (!appData.getFavouriteTrips().isEmpty()) {
            for (Trip trip : appData.getFavouriteTrips()) {
                tripsStartList.add(trip.getStartStation().getSiteID());
            }
        }
        return tripsStartList;
    }

    public ArrayList<Integer> getFavouriteTripsEndStrings() {
        ArrayList<Integer> tripsEndList = new ArrayList<>();
        if (!appData.getFavouriteTrips().isEmpty()) {
            for (Trip trip : appData.getFavouriteTrips()) {
                tripsEndList.add(trip.getEndStation().getSiteID());
            }
        }
        return tripsEndList;
    }
}
