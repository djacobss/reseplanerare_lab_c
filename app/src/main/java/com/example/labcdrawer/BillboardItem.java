package com.example.labcdrawer;

import java.io.Serializable;
import java.util.ArrayList;

public class BillboardItem implements Serializable {

    private String stationName,nextLineNumber,nextDisplayTime;
    private ArrayList<String> comingLines, comingDestinations, comingDisplayTimes;

    public BillboardItem(){

    }

    public BillboardItem(String stationName, String nextLineNumber, String nextDisplayTime, ArrayList<String> comingLines, ArrayList<String> comingDestinations, ArrayList<String> comingDisplayTimes){
        this.stationName = stationName;
        this.nextLineNumber = nextLineNumber;
        this.nextDisplayTime = nextDisplayTime;
        this.comingLines = comingLines;
        this.comingDestinations = comingDestinations;
        this.comingDisplayTimes = comingDisplayTimes;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getNextLineNumber() {
        return nextLineNumber;
    }

    public void setNextLineNumber(String nextLineNumber) {
        this.nextLineNumber = nextLineNumber;
    }

    public String getNextDisplayTime() {
        return nextDisplayTime;
    }

    public void setNextDisplayTime(String nextDisplayTime) {
        this.nextDisplayTime = nextDisplayTime;
    }

    public ArrayList<String> getComingLines() {
        return comingLines;
    }

    public void setComingLines(ArrayList<String> comingLines) {
        this.comingLines = comingLines;
    }

    public ArrayList<String> getComingDestinations() {
        return comingDestinations;
    }

    public void setComingDestinations(ArrayList<String> comingDestinations) {
        this.comingDestinations = comingDestinations;
    }

    public ArrayList<String> getComingDisplayTimes() {
        return comingDisplayTimes;
    }

    public void setComingDisplayTimes(ArrayList<String> comingDisplayTimes) {
        this.comingDisplayTimes = comingDisplayTimes;
    }
}
