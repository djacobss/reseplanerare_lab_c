package com.example.labcdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Fragment for the real time favourite instance.
 */
public class RealTimeFavouriteFragment extends Fragment {

    private static final String STATION_LIST = "stationList";

    private ArrayList<Integer> stationList;
    private Model model;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public RealTimeFavouriteFragment() {

    }

    public static RealTimeFavouriteFragment newInstance(ArrayList<Integer> listOfStations) {
        RealTimeFavouriteFragment fragment = new RealTimeFavouriteFragment();
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
            setHasOptionsMenu(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_real_time_favourite, container, false);
        recyclerView = view.findViewById(R.id.realTimeFavouriteRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new RecyclerFavouriteAdapter(model.getAppData().getFavouriteStations(), view.getContext(), new RecyclerFavouriteAdapter.FavItemClickListener() {
            @Override
            public void onFavItemClicked(LocationItem locationItem) {
                if (model.getAppData().getFavouriteStations().contains(locationItem)) {
                    model.getAppData().removeFavouriteStation(locationItem);
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}