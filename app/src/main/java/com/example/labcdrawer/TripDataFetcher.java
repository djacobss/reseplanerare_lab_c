package com.example.labcdrawer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class TripDataFetcher {

    public static void getJSONTripData(String startStationID, String endStationID, Context context, Model model) {

        String url = null;

        if (model.getTimeChoice() == TripsTimeChoice.NOW) {
            url = "https://api.sl.se/api2/TravelplannerV3_1/trip.json?key=763571fb51e54878ab1d9c937571d9a6&originextid=" + startStationID + "&destextid=" + endStationID + "&lang=se";
        } else if (model.getTimeChoice() == TripsTimeChoice.ARRIVE_AT) {
            url = "https://api.sl.se/api2/TravelplannerV3_1/trip.json?key=763571fb51e54878ab1d9c937571d9a6&originextid=" + startStationID + "&destextid=" + endStationID + "&lang=se&date=" + model.getDesiredDate() + "&time=" + model.getDesiredTime() + "&searchforarrival=1";
        } else if (model.getTimeChoice() == TripsTimeChoice.TRAVEL_AT) {
            url = "https://api.sl.se/api2/TravelplannerV3_1/trip.json?key=763571fb51e54878ab1d9c937571d9a6&originextid=" + startStationID + "&destextid=" + endStationID + "&lang=se&date=" + model.getDesiredDate() + "&time=" + model.getDesiredTime() + "&searchforarrival=0";
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> model.tripsDataReceived(response),
                error -> {
                    error.printStackTrace();
                    model.timeOutErrorMsg();
                });
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }


    public static void getJSONHomeTripData(String startStationID, String endStationID, Context context, Model model) {


        String url = "https://api.sl.se/api2/TravelplannerV3_1/trip.json?key=763571fb51e54878ab1d9c937571d9a6&originextid=" + startStationID + "&destextid=" + endStationID + "&lang=se";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> model.tripsDataReceived(response),
                error -> {
                    error.printStackTrace();
                    model.timeOutErrorMsg();
                });
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

}
