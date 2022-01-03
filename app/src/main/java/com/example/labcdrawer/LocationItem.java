package com.example.labcdrawer;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LocationItem implements Parcelable {

    private String placeName;
    private int siteID;
    private boolean isFavourite;

    public LocationItem(String placeName, int siteID) {
        this.placeName = placeName;
        this.siteID = siteID;
        isFavourite = false;
    }

    protected LocationItem(Parcel in) {
        placeName = in.readString();
        siteID = in.readInt();
        isFavourite = in.readByte() != 0;
    }

    public static final Creator<LocationItem> CREATOR = new Creator<LocationItem>() {
        @Override
        public LocationItem createFromParcel(Parcel in) {
            return new LocationItem(in);
        }

        @Override
        public LocationItem[] newArray(int size) {
            return new LocationItem[size];
        }
    };

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean getFavourite() {
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

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeName);
        dest.writeInt(siteID);
        dest.writeByte((byte) (isFavourite ? 1 : 0));
    }
}
