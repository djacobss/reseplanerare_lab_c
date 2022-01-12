package com.example.labcdrawer;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Model class for handling current instance data and logic.
 */
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
    private ReturnToFragment currentFragment;
    private TripsHomeFragment tripsHomeFragment;
    private ArrayList<TripItem> tripHomeItemResults;

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

    /**
     * Creates an ArrayList of LocationItems from the JSON parser for station searching and then notifies
     * the fragment to show the results of the parsing.
     * @param response JSON object collected in a data fetcher.
     */
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

    /**
     * Checks if a LocationItem exists in the favourite stations list in the AppData.
     * @param item A LocationItem to be checked.
     * @return true if item exists in the list, false if it doesn't.
     */
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

    /**
     * Creates an ArrayList of RealTimeItems collected from a data fetcher by calling a parser and then notifies
     * the activity to show the results.
     * @param response JSON object to be parsed.
     */
    public void realTimeDataReceived(JSONObject response) {
        RealTimeParser realTimeParser = new RealTimeParser();
        ArrayList<RealTimeItem> tempList = realTimeParser.parseRealTimeData(response, this);
        stationRealTimeActivity.showResults(tempList);
    }


    /**
     * Sets the last time the data was updated string from the string received in the parsing.
     * @param latestUpdate A date string from parsing.
     */
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

    /**
     * Creates an ArrayList of BillboardItems collected from a data fetcher by calling a parser and then notifies
     * the activity to show the results.
     * @param objects ArrayList of JSON objects collected in waitForResponse method.
     * @param siteIDs ArrayList of Site IDs collected in waitForResponse method.
     */
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

    /**
     * Gets called in the BillboardDataFetcher when a JSON object has been received and ads the object and Site ID to an ArrayList.
     * @param object JSON object from data fetcher.
     * @param siteID Site ID used to fetch the data.
     * @param context
     */
    public void waitForResponses(JSONObject object, int siteID, Context context) {
        billboardObjectContainer.add(object);
        billboardSiteIDContainer.add(siteID);
        if (billboardObjectContainer.size() < appData.getFavouriteStations().size()) {
            BillboardDataFetcher.getBillboardJSONData(appData.getFavouriteStations().get(billboardObjectContainer.size()).getSiteIDString(), context, this);
        } else if (billboardObjectContainer.size() >= appData.getFavouriteStations().size()) {
            billboardDataReceived(billboardObjectContainer, billboardSiteIDContainer);
        }
    }

    public void timeOutErrorMsg() {
        mainActivity.showTimeoutToast();
    }

    public void timeOutInStationActivity() {
        stationRealTimeActivity.showTimeout();
    }

    /**
     * Saved Serialized AppData
     */
    public void saveFromStationActivity() {
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

    /**
     * Loads Serialized AppData
     */
    public boolean loadFromStationActivity() {
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

    /**
     * Saved Serialized AppData
     */
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

    /**
     * Loads Serialized AppData
     */
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

    /**
     * Gets called in the TripStationDataFetcher when an object has been fetched.
     * Creates ArrayList of LocationItems and then notifies fragment to show the results.
     * @param response JSON object fetched in data fetcher.
     */
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

    /**
     * Gets called in the TripDataFetcher when an object has been fetched.
     * Checks which fragment is open and then uses the appropriate parser to make a list of items.
     * Notifies the fragment to show the results.
     * @param response JSON object fetcher in data fetcher.
     */
    public void tripsDataReceived(JSONObject response) {
        if (currentFragment == ReturnToFragment.TRIPS_SEARCH) {
            ArrayList<TripItem> tripItems = TripsJSONParser.parseTripsJSON(response, this);
            tripsSearchFragment.showTripsResults(tripItems);
        } else if (currentFragment == ReturnToFragment.TRIPS_HOME) {
            tripHomeItemResults.add(TripsJSONParser.parseTripsJSON(response, this).get(0));
            if (tripHomeItemResults.size() < appData.getFavouriteTrips().size()) {
                TripDataFetcher.getJSONHomeTripData(
                        appData.getFavouriteTrips().get(tripHomeItemResults.size()).getStartLocationID(),
                        appData.getFavouriteTrips().get(tripHomeItemResults.size()).getEndLocationID(), tripsHomeFragment.getViewContext(), this);
            } else if (tripHomeItemResults.size() >= appData.getFavouriteTrips().size()) {
                tripsHomeFragment.showResults(tripHomeItemResults);
            }
        }
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

    public ReturnToFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(ReturnToFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public TripsHomeFragment getTripsHomeFragment() {
        return tripsHomeFragment;
    }

    public void setTripsHomeFragment(TripsHomeFragment tripsHomeFragment) {
        this.tripsHomeFragment = tripsHomeFragment;
    }

    public ArrayList<TripItem> getTripHomeItemResults() {
        return tripHomeItemResults;
    }

    public void setTripHomeItemResults(ArrayList<TripItem> tripHomeItemResults) {
        this.tripHomeItemResults = tripHomeItemResults;
    }
}
