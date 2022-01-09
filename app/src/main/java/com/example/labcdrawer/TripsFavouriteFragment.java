package com.example.labcdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class TripsFavouriteFragment extends Fragment {

    private Model model;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public TripsFavouriteFragment() {

    }

    public static TripsFavouriteFragment newInstance() {
        TripsFavouriteFragment fragment = new TripsFavouriteFragment();
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
        View view = inflater.inflate(R.layout.fragment_trips_favourite, container, false);
        recyclerView = view.findViewById(R.id.tripsFavouriteRecyclerView);

        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new RecyclerTripsFavouriteAdapter(model.getAppData().getFavouriteTrips());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}