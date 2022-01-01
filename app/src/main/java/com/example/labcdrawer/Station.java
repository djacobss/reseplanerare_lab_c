package com.example.labcdrawer;

public class Station {

    private String name, address;
    private double coordinateLon, coordinateLat;
    private int siteID;

    public Station(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCoordinateLon() {
        return coordinateLon;
    }

    public void setCoordinateLon(double coordinateLon) {
        this.coordinateLon = coordinateLon;
    }

    public double getCoordinateLat() {
        return coordinateLat;
    }

    public void setCoordinateLat(double coordinateLat) {
        this.coordinateLat = coordinateLat;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }
}
