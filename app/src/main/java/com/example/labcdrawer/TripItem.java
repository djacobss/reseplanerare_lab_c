package com.example.labcdrawer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TripItem implements Serializable {

    private String startTime, endTime, startDate, endDate;
    private String endLocationName, startLocationName, startLocationID, endLocationID;
    private ArrayList<String> lineList, startLocations, endLocations, startTimes, endTimes, lineDirections;
    private ArrayList<TransportMode> modeOfTravel;
    private boolean isFavourite, isExpanded;
    private ArrayList<TripLineItem> tripLineItems;
    private ArrayList<TripSubItem> tripSubItems;

    public TripItem() {
        lineList = new ArrayList<>();
        startLocations = new ArrayList<>();
        endLocations = new ArrayList<>();
        startTimes = new ArrayList<>();
        endTimes = new ArrayList<>();
        lineDirections = new ArrayList<>();
        modeOfTravel = new ArrayList<>();
        tripLineItems = new ArrayList<>();
        tripSubItems = new ArrayList<>();
        isFavourite = false;
        isExpanded = false;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<String> getLineList() {
        return lineList;
    }

    public void setLineList(ArrayList<String> lineList) {
        this.lineList = lineList;
    }

    public ArrayList<String> getStartLocations() {
        return startLocations;
    }

    public void setStartLocations(ArrayList<String> startLocations) {
        this.startLocations = startLocations;
    }

    public ArrayList<String> getEndLocations() {
        return endLocations;
    }

    public void setEndLocations(ArrayList<String> endLocations) {
        this.endLocations = endLocations;
    }

    public ArrayList<String> getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(ArrayList<String> startTimes) {
        this.startTimes = startTimes;
    }

    public ArrayList<String> getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(ArrayList<String> endTimes) {
        this.endTimes = endTimes;
    }

    public String getEndLocationName() {
        return endLocationName;
    }

    public void setEndLocationName(String endLocationName) {
        this.endLocationName = endLocationName;
    }

    public String getStartLocationName() {
        return startLocationName;
    }

    public void setStartLocationName(String startLocationName) {
        this.startLocationName = startLocationName;
    }

    public String getStartLocationID() {
        return startLocationID;
    }

    public void setStartLocationID(String startLocationID) {
        this.startLocationID = startLocationID;
    }

    public String getEndLocationID() {
        return endLocationID;
    }

    public void setEndLocationID(String endLocationID) {
        this.endLocationID = endLocationID;
    }

    public ArrayList<TransportMode> getModeOfTravel() {
        return modeOfTravel;
    }

    public void setModeOfTravel(ArrayList<TransportMode> modeOfTravel) {
        this.modeOfTravel = modeOfTravel;
    }

    public ArrayList<String> getLineDirections() {
        return lineDirections;
    }

    public void setLineDirections(ArrayList<String> lineDirections) {
        this.lineDirections = lineDirections;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setupTripLineItems() {
        tripLineItems = new ArrayList<>();
        for (int i = 0; i < lineList.size(); i++) {
            TripLineItem tripLineItem = new TripLineItem(
                    lineList.get(i),
                    modeOfTravel.get(i)

            );
            tripLineItems.add(tripLineItem);
        }
    }

    public ArrayList<TripLineItem> getTripLineItems() {
        return tripLineItems;
    }

    public void setTripLineItems(ArrayList<TripLineItem> tripLineItems) {
        this.tripLineItems = tripLineItems;
    }

    public ArrayList<TripSubItem> getTripSubItems() {
        return tripSubItems;
    }

    public void setTripSubItems(ArrayList<TripSubItem> tripSubItems) {
        this.tripSubItems = tripSubItems;
    }

    public void setUpSubItems() {
        tripSubItems = new ArrayList<>();
        for (int i = 0; i < lineList.size(); i++) {
            TripSubItem tripSubItem = new TripSubItem(startLocations.get(i),
                    endLocations.get(i), startTimes.get(i), endTimes.get(i),
                    lineDirections.get(i), lineList.get(i), modeOfTravel.get(i));
            tripSubItems.add(tripSubItem);
        }
    }
}
