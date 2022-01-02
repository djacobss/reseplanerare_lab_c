package com.example.labcdrawer;

import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

public class Model {

    private AppData appData;
    private MainActivity mainActivity;
    private RealTimeSearchFragment realTimeSearchFragment;

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

    public void stationSearchDataReceived(JSONObject response) {
        ArrayList<SearchRecyclerItem> searchResultList = ParseSearchStation.parseStationData(response);
        for (SearchRecyclerItem searchItem : searchResultList) {
            setSearchItemFavourite(searchItem);
        }
        realTimeSearchFragment.showResults(searchResultList);
    }

    public void errorInStationSearchResponse(VolleyError error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            realTimeSearchFragment.searchTimeOutError();
        } else {
            realTimeSearchFragment.wrongInputError();
        }
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setRealTimeSearchFragment(RealTimeSearchFragment realTimeSearchFragment) {
        this.realTimeSearchFragment = realTimeSearchFragment;
    }

    public RealTimeSearchFragment getRealTimeSearchFragment() {
        return realTimeSearchFragment;
    }

    public void setSearchItemFavourite(SearchRecyclerItem item) {
        for (Station s : appData.getFavouriteStations()) {
            if (s.getSiteID() == item.getSiteID()) {
                item.setFavourite(true);
            } else {
                item.setFavourite(false);
            }
        }
    }
}
