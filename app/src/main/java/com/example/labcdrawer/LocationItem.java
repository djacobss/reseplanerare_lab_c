package com.example.labcdrawer;

public class LocationItem {

    private String placeName;
    private int siteID;
    private boolean isFavourite;

    public LocationItem(String placeName, int siteID){
        this.placeName = placeName;
        this.siteID = siteID;
        isFavourite = false;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean getFavourite(){
        return isFavourite;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID){
        this.siteID = siteID;
    }
}