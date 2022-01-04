package com.example.labcdrawer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class StationDataFetcher {

    public static void getJSONStationData(String searchString, Context context, Model model) {

        String url = "https://api.sl.se/api2/typeahead.json?key=939f2b004e7a4c16b24e48213abe680e&searchstring=" + searchString + "&stationsonly=True&maxresults=50";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    model.stationSearchDataReceived(response);
                },
                error -> {
                    model.errorInStationSearchResponse(error);
                    error.printStackTrace();
                });
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

}
