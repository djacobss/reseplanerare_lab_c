package com.example.labcdrawer;

import java.util.Date;

public class Trip {

    private Station startStation, endStation;
    private Date travelTime;
    private boolean arriveAtDate;   //if arriveAtDate is true, then the user wishes to arrive at the specified time. Otherwise they wish to start travelling then.

    public Trip(Station startStation, Station endStation, Date travelTime, boolean arriveAtDate){
        this.arriveAtDate = arriveAtDate;
        this.endStation = endStation;
        this.startStation = startStation;
        this.travelTime = travelTime;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
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
