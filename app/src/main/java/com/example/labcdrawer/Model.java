package com.example.labcdrawer;

import android.util.Log;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Model {

    private AppData appData;
    private MainActivity mainActivity;
    private RealTimeSearchFragment searchFragment;
    private StationRealTimeActivity stationRealTimeActivity;
    private RealTimeBillBoardFragment billBoardFragment;
    private TripsSearchFragment tripsSearchFragment;
    private String desiredTime, desiredDate;
    private ArrayList<JSONObject> billboardObjectContainer;
    private ArrayList<Integer> billboardSiteIDContainer;
    public static final String FILE_NAME = "travelApp.dat";
    private TripsTimeChoice timeChoice;
    private String currentStartLocID, currentEndLocID;

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

    public ArrayList<String> getFavouriteStationStrings() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        if (!appData.getFavouriteStations().isEmpty()) {
            for (LocationItem station : appData.getFavouriteStations()) {
                stringArrayList.add(Integer.toString(station.getSiteID()));
            }
        }
        return stringArrayList;
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
        ArrayList<RealTimeItem> tempList = realTimeParser.parseRealTimeData(response, this);
        stationRealTimeActivity.showResults(tempList);
    }


    public void setLastUpdatedRealTime(String latestUpdate) {
        latestUpdate = latestUpdate.substring(0, Math.min(latestUpdate.length(), 16));
        StringBuilder builder = new StringBuilder(latestUpdate);
        builder.setCharAt(10, ' ');
        builder.insert(0, "Senast Uppdaterat: ");
        appData.setLastUpdatedRealTime(builder.toString());
    }

    public String getLastUpdatedRealTime() {
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
        ArrayList<BillboardItem> billboardItems = BillboardParser.parseBillboardJSON(objects, this, siteIDs);
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

    public void waitForResponses(JSONObject object, int size, int siteID) {
        billboardObjectContainer.add(object);
        billboardSiteIDContainer.add(siteID);
        if (billboardObjectContainer.size() == size) {
            billboardDataReceived(billboardObjectContainer, billboardSiteIDContainer);
        }
    }

    public void timeOutErrorMsg() {
        mainActivity.showTimeoutToast();
    }

    public void saveFromStationActivity(){
        try {
            FileOutputStream fos = stationRealTimeActivity.openFileOutput(FILE_NAME, stationRealTimeActivity.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(appData);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadFromStationActivity(){
        try {
            FileInputStream fis = stationRealTimeActivity.openFileInput(FILE_NAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            appData = (AppData) is.readObject();
            is.close();
            fis.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public void save() {
        try {
            FileOutputStream fos = mainActivity.openFileOutput(FILE_NAME, mainActivity.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(appData);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean load() {
        try {
            FileInputStream fis = mainActivity.openFileInput(FILE_NAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            appData = (AppData) is.readObject();
            is.close();
            fis.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public TripsSearchFragment getTripsSearchFragment() {
        return tripsSearchFragment;
    }

    public void setTripsSearchFragment(TripsSearchFragment tripsSearchFragment) {
        this.tripsSearchFragment = tripsSearchFragment;
    }

    public void stationTripSearchDataReceived(JSONObject response) {
        ArrayList<LocationItem> searchResultList = SearchStationParser.parseStationData(response);
        tripsSearchFragment.showStationResults(searchResultList);
    }

    public TripsTimeChoice getTimeChoice() {
        return timeChoice;
    }

    public void setTimeChoice(TripsTimeChoice timeChoice) {
        this.timeChoice = timeChoice;
    }

    public String getDesiredTime() {
        return desiredTime;
    }

    public void setDesiredTime(String desiredTime) {
        this.desiredTime = desiredTime;
    }

    public String getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(String desiredDate) {
        this.desiredDate = desiredDate;
    }

    public void tripsDataReceived(JSONObject response) {
        ArrayList<TripItem> tripItems = TripsJSONParser.parseTripsJSON(response, this);
        tripsSearchFragment.showTripsResults(tripItems);
    }

    public String getCurrentStartLocID() {
        return currentStartLocID;
    }

    public void setCurrentStartLocID(String currentStartLocID) {
        this.currentStartLocID = currentStartLocID;
    }

    public String getCurrentEndLocID() {
        return currentEndLocID;
    }

    public void setCurrentEndLocID(String currentEndLocID) {
        this.currentEndLocID = currentEndLocID;
    }
}
