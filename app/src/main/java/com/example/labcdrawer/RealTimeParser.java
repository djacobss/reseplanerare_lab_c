package com.example.labcdrawer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RealTimeParser {

    RealTimeParser() {

    }

    public ArrayList<RealTimeItem> parseRealTimeData(JSONObject response, Model model) {
        ArrayList<RealTimeItem> tempList = new ArrayList<>();
        try {
            JSONObject responseData = response.getJSONObject("ResponseData");
            model.setLastUpdatedRealTime(responseData.getString("LatestUpdate"));
            JSONArray transportData = setJSONArrayFromTransportMode(responseData, model);
            for (int i = 0; i < transportData.length(); i++) {
                JSONObject tempObject = transportData.getJSONObject(i);
                RealTimeItem newItem = new RealTimeItem(
                        tempObject.getString("Destination"),
                        tempObject.getString("DisplayTime"),
                        tempObject.getString("TransportMode"),
                        Integer.parseInt(tempObject.getString("LineNumber"))
                );
                tempList.add(newItem);
            }
        } catch (JSONException e) {
            Log.e("Error: ", "Couldn't find response data");
            e.printStackTrace();
        }


        return tempList;
    }

    public JSONArray setJSONArrayFromTransportMode(JSONObject responseData, Model model) {
        JSONArray tempArray = new JSONArray();
        switch (model.getAppData().getRealTimeTransportMode()) {
            case BUS:
                try {
                    tempArray = responseData.getJSONArray("Buses");
                } catch (JSONException e) {
                    Log.e("Error: ", "Couldn't find desired transport data");
                    e.printStackTrace();
                }
                break;
            case SHIP:
                try {
                    tempArray = responseData.getJSONArray("Ships");
                } catch (JSONException e) {
                    Log.e("Error: ", "Couldn't find desired transport data");
                    e.printStackTrace();
                }
                break;
            case TRAM:
                try {
                    tempArray = responseData.getJSONArray("Trams");
                } catch (JSONException e) {
                    Log.e("Error: ", "Couldn't find desired transport data");
                    e.printStackTrace();
                }
                break;
            case METRO:
                try {
                    tempArray = responseData.getJSONArray("Metros");
                } catch (JSONException e) {
                    Log.e("Error: ", "Couldn't find desired transport data");
                    e.printStackTrace();
                }
                break;
            case TRAIN:
                try {
                    tempArray = responseData.getJSONArray("Trains");
                } catch (JSONException e) {
                    Log.e("Error: ", "Couldn't find desired transport data");
                    e.printStackTrace();
                }
                break;
        }
        return tempArray;
    }

}
