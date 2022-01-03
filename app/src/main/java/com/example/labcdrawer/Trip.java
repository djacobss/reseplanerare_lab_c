package com.example.labcdrawer;

import java.util.Date;

public class Trip {

    private LocationItem startStation, endStation;
    private Date travelTime;
    private boolean arriveAtDate;   //if arriveAtDate is true, then the user wishes to arrive at the specified time. Otherwise they wish to start travelling then.

    public Trip(LocationItem startStation, LocationItem endStation, Date travelTime, boolean arriveAtDate){
        this.arriveAtDate = arriveAtDate;
        this.endStation = endStation;
        this.startStation = startStation;
        this.travelTime = travelTime;
    }

    public LocationItem getStartStation() {
        return startStation;
    }

    public void setStartStation(LocationItem startStation) {
        this.startStation = startStation;
    }

    public LocationItem getEndStation() {
        return endStation;
    }

    public void setEndStation(LocationItem endStation) {
        this.endStation = endStation;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date travelTime) {
        this.travelTime = travelTime;
    }

    public boolean isArriveAtDate() {
        return arriveAtDate;
    }

    public void setArriveAtDate(boolean arriveAtDate) {
        this.arriveAtDate = arriveAtDate;
    }
}
