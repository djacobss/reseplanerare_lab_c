package com.example.labcdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RealTimeSearchFragment extends Fragment {

    private static final String STATION_LIST = "stationList";

    private ArrayList<Integer> stationList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SearchRecyclerItem> searchRecyclerItems;
    private View mainView;

    public RealTimeSearchFragment() {

    }

    public static RealTimeSearchFragment newInstance(ArrayList<Integer> listOfStations) {
        RealTimeSearchFragment fragment = new RealTimeSearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_real_time_search, container, false);
        mainView = view;
        searchRecyclerItems = new ArrayList<>();
        searchRecyclerItems.add(new SearchRecyclerItem("Fenixvägen (Danderyd)"));
        searchRecyclerItems.add(new SearchRecyclerItem("Fenixvägen (Danderyd)"));
        recyclerView = view.findViewById(R.id.realTimeSearchRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new RecycleSearchAdapter(searchRecyclerItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public View getMainView() {
        return mainView;
    }
}