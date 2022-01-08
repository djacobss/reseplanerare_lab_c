package com.example.labcdrawer;

import java.io.Serializable;
import java.util.ArrayList;

public class BillboardItem implements Serializable {

    private String stationName, nextLineNumber, nextDisplayTime;
    private ArrayList<BillboardSubItem> billboardSubItems;
    private boolean isOpen;

    public BillboardItem() {
        isOpen = false;
    }

    public BillboardItem(String stationName, String nextLineNumber, String nextDisplayTime, ArrayList<BillboardSubItem> billboardSubItems) {
        this.stationName = stationName;
        this.nextLineNumber = nextLineNumber;
        this.nextDisplayTime = nextDisplayTime;
        this.billboardSubItems = billboardSubItems;
        isOpen = false;
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

    public ArrayList<BillboardSubItem> getBillboardSubItems() {
        return billboardSubItems;
    }

    public void setBillboardSubItems(ArrayList<BillboardSubItem> billboardSubItems) {
        this.billboardSubItems = billboardSubItems;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
