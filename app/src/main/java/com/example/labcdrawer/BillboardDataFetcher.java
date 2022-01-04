package com.example.labcdrawer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public class BillboardDataFetcher {

    public static void getBillboardJSONData(ArrayList<String> stringArrayList, Context context, Model model) {
        ArrayList<JSONObject> objects = new ArrayList<>();

        for (String string : stringArrayList) {

            String url = "https://api.sl.se/api2/realtimedeparturesV4.json?key=41f722982a6f48609a4e38ea1f38eb47&siteid=" + string + "&timewindow=60";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    response -> objects.add(response),
                    error -> {
                        error.printStackTrace();
                        Log.e("Error: ","Data Fetching failed");
                        //TODO: error MSG
                    });

        }
        model.billboardDataReceived(objects);
    }

}
