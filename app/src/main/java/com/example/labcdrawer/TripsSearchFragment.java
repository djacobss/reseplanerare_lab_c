package com.example.labcdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TripsSearchFragment extends Fragment {

    private ArrayList<Integer> stationListStart;
    private ArrayList<Integer> stationListEnd;


    public TripsSearchFragment() {

    }

    public static TripsSearchFragment newInstance() {
        TripsSearchFragment fragment = new TripsSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setHasOptionsMenu(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trips_search, container, false);
    }
}