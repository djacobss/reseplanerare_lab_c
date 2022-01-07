package com.example.labcdrawer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TripItem implements Serializable {

    private String startStation, endStation, startTime, endTime;
    private ArrayList<String> lineList, startLocations, endLocations, startTimes, endTimes;
    private Date travelTime;
    private TripsTimeChoice timeChoice;   //if arriveAtDate is true, then the user wishes to arrive at the specified time. Otherwise they wish to start travelling then.

    public TripItem(){
        timeChoice = TripsTimeChoice.NOW;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
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

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date travelTime) {
        this.travelTime = travelTime;
    }

    public TripsTimeChoice getTimeChoice() {
        return timeChoice;
    }

    public void setTimeChoice(TripsTimeChoice timeChoice) {
        this.timeChoice = timeChoice;
    }
}
