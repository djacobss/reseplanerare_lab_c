package com.example.labcdrawer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class that has one method for parsing the Billboard JSON data.
 */

public class BillboardParser {

    /**
     * Parses the JSON data for the billboard.
     * @param objectArrayList List of JSON objects from the billboard data fetcher.
     * @param model
     * @param siteIDList List of site IDs for the stations
     * @return a list of billboard items with correctly assigned data.
     */
    public static ArrayList<BillboardItem> parseBillboardJSON(ArrayList<JSONObject> objectArrayList, Model model, ArrayList<Integer> siteIDList) {
        ArrayList<BillboardItem> billboardItems = new ArrayList<>();
        int index = 0;
        for (JSONObject object : objectArrayList) {

            try {
                BillboardItem billboardItem = new BillboardItem();
                JSONObject responseData = object.getJSONObject("ResponseData");
                RealTimeParser realTimeParser = new RealTimeParser();
                JSONArray jsonArray = realTimeParser.setJSONArrayFromTransportMode(responseData, model);
                if (jsonArray.length() > 0) {
                    ArrayList<BillboardSubItem> billboardSubItems = new ArrayList<>();

                    for (LocationItem item : model.getAppData().getFavouriteStations()) {
                        if (item.getSiteID() == siteIDList.get(index)) {
                            billboardItem.setStationName(item.getPlaceName());
                        }
                    }
                    billboardItem.setNextLineNumber(jsonArray.getJSONObject(0).getString("LineNumber"));
                    billboardItem.setNextDisplayTime(jsonArray.getJSONObject(0).getString("DisplayTime"));

                    for (int i = 0; i < Math.min(6, jsonArray.length()); i++) {
                        billboardSubItems.add(new BillboardSubItem(
                                jsonArray.getJSONObject(i).getString("LineNumber"),
                                jsonArray.getJSONObject(i).getString("Destination"),
                                jsonArray.getJSONObject(i).getString("DisplayTime")
                        ));
                    }
                    billboardItem.setBillboardSubItems(billboardSubItems);
                    billboardItems.add(billboardItem);
                } else {
                    ArrayList<BillboardSubItem> billboardSubItems = new ArrayList<>();

                    for (LocationItem item : model.getAppData().getFavouriteStations()) {
                        if (item.getSiteID() == siteIDList.get(index)) {
                            billboardItem.setStationName(item.getPlaceName());
                        }
                    }

                    billboardItem.setNextLineNumber("-");
                    billboardItem.setNextDisplayTime("Inga avgångar");
                    billboardItem.setBillboardSubItems(billboardSubItems);
                    billboardItems.add(billboardItem);
                }
            } catch (JSONException e) {
                Log.e("Error: ", "JSON parsing error");
                e.printStackTrace();
            }
            index++;
        }
        return billboardItems;
    }

}
