package com.example.labcdrawer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchStationParser {

    public static ArrayList<LocationItem> parseStationData(JSONObject response) {
        ArrayList<LocationItem> locationItems = new ArrayList<>();
        try {
            JSONArray responseData = response.getJSONArray("ResponseData");
            for (int i = 0; i < responseData.length(); i++) {
                JSONObject tempJsonObject = responseData.getJSONObject(i);
                LocationItem newItem = new LocationItem(
                        tempJsonObject.getString("Name"),
                        Integer.parseInt(tempJsonObject.getString("SiteId")));
                locationItems.add(newItem);
            }
        } catch (JSONException e) {
            Log.e("Parsing Error", "Couldnt find Response Data");
            e.printStackTrace();
        }
        return locationItems;
    }

}
