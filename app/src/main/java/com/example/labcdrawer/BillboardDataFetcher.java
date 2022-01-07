package com.example.labcdrawer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class BillboardDataFetcher {

    public static void getBillboardJSONData(ArrayList<String> stringArrayList, Context context, Model model) {

        for (String string : stringArrayList) {

            String url = "https://api.sl.se/api2/realtimedeparturesV4.json?key=41f722982a6f48609a4e38ea1f38eb47&siteid=" + string + "&timewindow=60";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    response -> {
                        model.waitForResponses(response,stringArrayList.size(),Integer.parseInt(string));
                    },
                    error -> {
                        error.printStackTrace();
                        Log.e("Error: ","Data Fetching failed");
                        model.timeOutErrorMsg();
                    });
            jsonObjectRequest.setShouldCache(false);
            Volley.newRequestQueue(context).add(jsonObjectRequest);
        }
    }

}
