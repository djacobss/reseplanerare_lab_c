package com.example.labcdrawer;

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
                JSONObject legList = tripArray.getJSONObject(i);
                JSONArray legArray = legList.getJSONArray("Leg");
                ArrayList<JSONObject> listOfLegInfo = new ArrayList<>();
                ArrayList<JSONObject> listOfOriginObjects = new ArrayList<>();
                ArrayList<JSONObject> listOfDestinationObjects = new ArrayList<>();
                ArrayList<JSONObject> listOfProductObjects = new ArrayList<>();
                tripItem.setStartLocationID(model.getCurrentStartLocID());
                tripItem.setEndLocationID(model.getCurrentEndLocID());
                tripItem.setStartLocationName(legArray.getJSONObject(0).getJSONObject("Origin").getString("name"));
                tripItem.setStartTime(legArray.getJSONObject(0).getJSONObject("Origin").getString("time").substring(0, 5));
                tripItem.setStartDate(legArray.getJSONObject(0).getJSONObject("Origin").getString("date"));
                tripItem.setEndLocationName(legArray.getJSONObject(legArray.length() - 1).getJSONObject("Destination").getString("name"));
                tripItem.setEndTime(legArray.getJSONObject(legArray.length() - 1).getJSONObject("Origin").getString("time").substring(0, 5));
                tripItem.setEndDate(legArray.getJSONObject(legArray.length() - 1).getJSONObject("Origin").getString("date"));
                for (int j = 0; j < legArray.length(); j++) {
                    listOfLegInfo.add(legArray.getJSONObject(j));
                }
                for (JSONObject legObject : listOfLegInfo) {
                    if (legObject.getString("type").equals("WALK")) {
                        tripItem.getModeOfTravel().add(TransportMode.WALK);
                        tripItem.getLineDirections().add(legObject.getString("dist") + "m");
                    } else {
                        tripItem.getModeOfTravel().add(getTravelMode(legObject));
                        listOfProductObjects.add(legObject.getJSONObject("Product"));
                        tripItem.getLineDirections().add(legObject.getString("direction"));
                    }
                    listOfOriginObjects.add(legObject.getJSONObject("Origin"));
                    listOfDestinationObjects.add(legObject.getJSONObject("Destination"));
                }
                for (JSONObject originObject : listOfOriginObjects) {
                    tripItem.getStartLocations().add(originObject.getString("name"));
                    tripItem.getStartTimes().add(originObject.getString("time").substring(0, 5));
                }
                for (JSONObject destinationObject : listOfDestinationObjects) {
                    tripItem.getEndLocations().add(destinationObject.getString("name"));
                    tripItem.getEndTimes().add(destinationObject.getString("time").substring(0, 5));
                }
                if (!listOfProductObjects.isEmpty()) {
                    for (JSONObject productObject : listOfProductObjects) {
                        tripItem.getLineList().add(productObject.getString("line"));
                    }
                } else {
                    tripItem.getLineList().add("Gång");
                }
                tripItems.add(tripItem);
            }
        } catch (JSONException e) {
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


