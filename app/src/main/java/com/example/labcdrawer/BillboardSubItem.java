package com.example.labcdrawer;

import java.io.Serializable;

public class BillboardSubItem implements Serializable {

    private String line, destination, displayTime;

    public BillboardSubItem() {

    }

    public BillboardSubItem(String line, String destination, String displayTime) {
        this.line = line;
        this.destination = destination;
        this.displayTime = displayTime;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

}
