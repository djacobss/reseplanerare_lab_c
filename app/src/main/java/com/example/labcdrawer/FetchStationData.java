package com.example.labcdrawer;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

public class FetchStationData {

    public static void getJSONStationData(String searchString, Model model) {

        String url = "https://api.sl.se/api2/typeahead.json?key=939f2b004e7a4c16b24e48213abe680e&searchstring=" + searchString + "&stationsonly=True&maxresults=50";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> model.stationSearchDataReceived(response),
                error -> {
                    model.errorInStationSearchResponse(error);
                    error.printStackTrace();
                });

    }

}
