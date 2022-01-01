package com.example.labcdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TripsHomeFragment extends Fragment {

    private static final String STATION_LIST_START = "stationListStart";
    private static final String STATION_LIST_END = "stationListEnd";

    private ArrayList<Integer> stationListStart;
    private ArrayList<Integer> stationListEnd;

    public TripsHomeFragment() {

    }

    public static TripsHomeFragment newInstance(ArrayList<Integer> startStations, ArrayList<Integer> endStations) {
        TripsHomeFragment fragment = new TripsHomeFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(STATION_LIST_START, startStations);
        args.putIntegerArrayList(STATION_LIST_END, endStations);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationListStart = getArguments().getIntegerArrayList(STATION_LIST_START);
            stationListEnd = getArguments().getIntegerArrayList(STATION_LIST_END);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trips_home, container, false);
    }
}