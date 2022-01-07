package com.example.labcdrawer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class RealTimeDataFetcher {

    public static void getJSONRealTimeData(String siteID, Context context, Model model) {

        String url = "https://api.sl.se/api2/realtimedeparturesV4.json?key=41f722982a6f48609a4e38ea1f38eb47&siteid=" + siteID + "&timewindow=60";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    model.realTimeDataReceived(response);
                },
                error -> {
                    model.timeOutErrorMsg();
                    error.printStackTrace();
                });
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

}
