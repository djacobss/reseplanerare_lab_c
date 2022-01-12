package com.example.labcdrawer;

/**
 * Class containing the required members for showing a real time item.
 */
public class RealTimeItem {

    private String busDestination, timeLeft, transportMode, lineNumber;

    public RealTimeItem() {

    }

    public RealTimeItem(String busDestination, String timeLeft, String transportMode, String lineNumber) {
        this.lineNumber = lineNumber;
        this.busDestination = busDestination;
        this.transportMode = transportMode;
        this.timeLeft = timeLeft;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getLineNumberString() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
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

    public TransportMode transportModeConverter() {
        switch (transportMode) {
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
