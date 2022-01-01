package com.example.labcdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RealTimeBillBoardFragment extends Fragment {


    private static final String STATION_LIST = "stationList";

    private ArrayList<Integer> stationList;

    public RealTimeBillBoardFragment() {

    }

    public static RealTimeBillBoardFragment newInstance(ArrayList<Integer> listOfStations) {
        RealTimeBillBoardFragment fragment = new RealTimeBillBoardFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(STATION_LIST, listOfStations);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationList = getArguments().getIntegerArrayList(STATION_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_real_time_bill_board, container, false);
    }
}