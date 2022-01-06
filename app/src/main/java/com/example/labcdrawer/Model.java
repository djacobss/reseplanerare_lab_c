package com.example.labcdrawer;

import android.util.Log;

import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Model {

    private AppData appData;
    private MainActivity mainActivity;
    private RealTimeSearchFragment searchFragment;
    private StationRealTimeActivity stationRealTimeActivity;
    private RealTimeBillBoardFragment billBoardFragment;
    private ArrayList<JSONObject> billboardObjectContainer;
    private ArrayList<Integer> billboardSiteIDContainer;

    public Model() {
        appData = new AppData();
        billboardObjectContainer = new ArrayList<>();
        billboardSiteIDContainer = new ArrayList<>();
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }

    public AppData getAppData() {
        return appData;
    }

    public ArrayList<Integer> getFavouriteStationInts() {
        ArrayList<Integer> stationStringList = new ArrayList<>();
        if (!appData.getFavouriteStations().isEmpty()) {
            for (LocationItem station : appData.getFavouriteStations()) {
                stationStringList.add(station.getSiteID());
            }
        }
        return stationStringList;
    }

    public ArrayList<String> getFavouriteStationStrings(){
        ArrayList<String> stringArrayList = new ArrayList<>();
        if(!appData.getFavouriteStations().isEmpty()){
            for (LocationItem station : appData.getFavouriteStations()) {
                stringArrayList.add(Integer.toString(station.getSiteID()));
            }
        }
        return stringArrayList;
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
        ArrayList<LocationItem> searchResultList = SearchStationParser.parseStationData(response);
        int index = 0;
        for (LocationItem searchItem : searchResultList) {
            if (setSearchItemFavourite(searchItem)) {
                searchResultList.get(index).setFavourite(true);
            } else {
                searchResultList.get(index).setFavourite(false);
            }
            index++;
        }
        searchFragment.showResults(searchResultList);
    }

    public void errorInStationSearchResponse(VolleyError error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            searchFragment.searchTimeOutError();
        } else {
            searchFragment.wrongInputError();
        }
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setRealTimeSearchFragment(RealTimeSearchFragment realTimeSearchFragment) {
        this.searchFragment = realTimeSearchFragment;
    }

    public RealTimeSearchFragment getRealTimeSearchFragment() {
        return searchFragment;
    }

    public boolean setSearchItemFavourite(LocationItem item) {
        if (!appData.getFavouriteStations().isEmpty()) {
            ArrayList<LocationItem> tempList = appData.getFavouriteStations();
            for (LocationItem i : tempList) {
                if (i.getPlaceName().equals(item.getPlaceName())) {
                    item.setFavourite(true);
                    return true;
                }
            }
        }
        item.setFavourite(false);
        return false;
    }

    public boolean addFavouriteStation(LocationItem station) {
        return appData.addFavouriteStation(station);
    }

    public boolean removeFavouriteStation(LocationItem station) {
        return appData.removeFavouriteStation(station);
    }

    public void realTimeDataReceived(JSONObject response) {
        RealTimeParser realTimeParser = new RealTimeParser();
        ArrayList<RealTimeItem> tempList = realTimeParser.parseRealTimeData(response,this);
        stationRealTimeActivity.showResults(tempList);
    }

    public void errorInRealTimeResponse(VolleyError error) {
    }

    public void setLastUpdatedRealTime(String latestUpdate) {
        latestUpdate = latestUpdate.substring(0,Math.min(latestUpdate.length(),16));
        StringBuilder builder = new StringBuilder(latestUpdate);
        builder.setCharAt(10,' ');
        builder.insert(0,"Senast Uppdaterat: ");
        appData.setLastUpdatedRealTime(builder.toString());
    }

    public String getLastUpdatedRealTime(){
        return appData.getLastUpdatedRealTime();
    }

    public RealTimeSearchFragment getSearchFragment() {
        return searchFragment;
    }

    public void setSearchFragment(RealTimeSearchFragment searchFragment) {
        this.searchFragment = searchFragment;
    }

    public StationRealTimeActivity getStationRealTimeActivity() {
        return stationRealTimeActivity;
    }

    public void setStationRealTimeActivity(StationRealTimeActivity stationRealTimeActivity) {
        this.stationRealTimeActivity = stationRealTimeActivity;
    }

    public void billboardDataReceived(ArrayList<JSONObject> objects, ArrayList<Integer> siteIDs) {
        ArrayList<BillboardItem> billboardItems = BillboardParser.parseBillboardJSON(objects,this, siteIDs);
        billboardObjectContainer.clear();
        billboardSiteIDContainer.clear();
        billBoardFragment.showResults(billboardItems);
    }

    public RealTimeBillBoardFragment getBillBoardFragment() {
        return billBoardFragment;
    }

    public void setBillBoardFragment(RealTimeBillBoardFragment billBoardFragment) {
        this.billBoardFragment = billBoardFragment;
    }

    public void waitForResponses(JSONObject object, int size, int siteID){
        billboardObjectContainer.add(object);
        billboardSiteIDContainer.add(siteID);
        for (Integer i : billboardSiteIDContainer) {
            Log.e("Test in Model 1: ", "OB container: " + billboardObjectContainer.size() + "SiteID Container: " + billboardSiteIDContainer.size());
        }
        if(billboardObjectContainer.size() == size){
            billboardDataReceived(billboardObjectContainer, billboardSiteIDContainer);
        }
    }
}
