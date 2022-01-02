package com.example.labcdrawer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseSearchStation {

    public static ArrayList<SearchRecyclerItem> parseStationData(JSONObject response){
        ArrayList<SearchRecyclerItem> searchRecyclerItems = new ArrayList<>();
        try {
            JSONArray responseData = response.getJSONArray("ResponseData");
            for (int i = 0; i < responseData.length(); i++) {
                JSONObject tempJsonObject = responseData.getJSONObject(i);
                SearchRecyclerItem newItem = new SearchRecyclerItem(
                        tempJsonObject.getString("Name"),
                        Integer.parseInt(tempJsonObject.getString("SiteId")));
                searchRecyclerItems.add(newItem);
            }
        } catch (JSONException e) {
            Log.e("Parsing Error","Couldnt find Response Data");
            e.printStackTrace();
        }
        return searchRecyclerItems;
    }

}
