package com.example.labcdrawer;

public class RealTimeItem {

    private String busDestination, timeLeft, transportMode;
    private int lineNumber;

    public RealTimeItem() {

    }

    public RealTimeItem(String busDestination, String timeLeft, String transportMode,int lineNumber) {
        this.lineNumber = lineNumber;
        this.busDestination = busDestination;
        this.transportMode = transportMode;
        this.timeLeft = timeLeft;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLineNumberString(){
        return Integer.toString(lineNumber);
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getBusDestination() {
        return busDestination;
    }

    public void setBusDestination(String busDestination) {
        this.busDestination = busDestination;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public TransportMode transportModeConverter(){
        switch (transportMode){
            case "BUS":
                return TransportMode.BUS;
            case "METRO":
                return TransportMode.METRO;
            case "SHIP":
                return TransportMode.SHIP;
            case "TRAM":
                return TransportMode.TRAM;
            case "TRAIN":
                return TransportMode.TRAIN;
        }
        return TransportMode.BUS;
    }
}
