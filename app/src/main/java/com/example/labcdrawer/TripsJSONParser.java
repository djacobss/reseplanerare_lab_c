package com.example.labcdrawer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TripsJSONParser {

    public static ArrayList<TripItem> parseTripsJSON(JSONObject jsonObject, Model model) {
        ArrayList<TripItem> tripItems = new ArrayList<>();
        try {
            JSONArray tripArray = jsonObject.getJSONArray("Trip");
            for (int i = 0; i < tripArray.length(); i++) {
                TripItem tripItem = new TripItem();
                JSONObject tripObject = tripArray.getJSONObject(i);
                JSONObject legList = tripObject.getJSONObject("LegList");
                JSONArray legArray = legList.getJSONArray("Leg");
                ArrayList<JSONObject> listOfLegInfo = new ArrayList<>();
                ArrayList<JSONObject> listOfOriginObjects = new ArrayList<>();
                ArrayList<JSONObject> listOfDestinationObjects = new ArrayList<>();
                tripItem.setStartLocationID(model.getCurrentStartLocID());
                tripItem.setEndLocationID(model.getCurrentEndLocID());
                tripItem.setStartLocationName(legArray.getJSONObject(0).getJSONObject("Origin").getString("name"));
                tripItem.setStartTime(legArray.getJSONObject(0).getJSONObject("Origin").getString("time").substring(0, 5));
                tripItem.setStartDate(legArray.getJSONObject(0).getJSONObject("Origin").getString("date"));
                tripItem.setEndLocationName(legArray.getJSONObject(legArray.length() - 1).getJSONObject("Destination").getString("name"));
                tripItem.setEndTime(legArray.getJSONObject(legArray.length() - 1).getJSONObject("Destination").getString("time").substring(0, 5));
                tripItem.setEndDate(legArray.getJSONObject(legArray.length() - 1).getJSONObject("Destination").getString("date"));
                for (int j = 0; j < legArray.length(); j++) {
                    listOfLegInfo.add(legArray.getJSONObject(j));
                }
                for (JSONObject legObject : listOfLegInfo) {
                    if(!legObject.getString("type").equals("WALK")) {
                        tripItem.getModeOfTravel().add(getTravelMode(legObject));
                        tripItem.getLineList().add(legObject.getJSONObject("Product").getString("line"));
                        tripItem.getLineDirections().add(legObject.getString("direction"));
                        listOfOriginObjects.add(legObject.getJSONObject("Origin"));
                        listOfDestinationObjects.add(legObject.getJSONObject("Destination"));
                    }
                }
                for (JSONObject originObject : listOfOriginObjects) {
                    tripItem.getStartLocations().add(originObject.getString("name"));
                    tripItem.getStartTimes().add(originObject.getString("time").substring(0, 5));
                }
                for (JSONObject destinationObject : listOfDestinationObjects) {
                    tripItem.getEndLocations().add(destinationObject.getString("name"));
                    tripItem.getEndTimes().add(destinationObject.getString("time").substring(0, 5));
                }
                tripItems.add(tripItem);
            }
        } catch (JSONException e) {
            //TODO ERROR MSG
            e.printStackTrace();
        }
        return tripItems;
    }

    private static TransportMode getTravelMode(JSONObject object) throws JSONException {
        switch (object.getString("category")) {
            case "BUS":
                return TransportMode.BUS;
            case "MET":
                return TransportMode.METRO;
            case "TRN":
                return TransportMode.TRAIN;
            case "SHP":
                return TransportMode.SHIP;
            case "TRM":
                return TransportMode.TRAM;
        }
        return TransportMode.BUS;
    }

}


