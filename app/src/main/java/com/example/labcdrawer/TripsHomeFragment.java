package com.example.labcdrawer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TripsHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Model model;

    public TripsHomeFragment() {

    }

    public static TripsHomeFragment newInstance() {
        TripsHomeFragment fragment = new TripsHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips_home, container, false);

        recyclerView = view.findViewById(R.id.tripsHomeRecyclerView);

        model.setTripsHomeFragment(this);
        model.setTripHomeItemResults(new ArrayList<>());
        model.setCurrentFragment(ReturnToFragment.TRIPS_HOME);

        if (!model.getAppData().getFavouriteTrips().isEmpty()) {
            TripDataFetcher.getJSONHomeTripData(model.getAppData().getFavouriteTrips().get(0).getStartLocationID(), model.getAppData().getFavouriteTrips().get(0).getEndLocationID(), view.getContext(), model);
        }

        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void showResults(ArrayList<TripItem> tripItems) {
        layoutManager = new LinearLayoutManager(getView().getContext());
        adapter = new RecyclerTripsAdapter(tripItems, getView().getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.billboard_refresh_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.billboardOptionsItemRefresh) {
            model.setTripHomeItemResults(new ArrayList<>());
            if (!model.getAppData().getFavouriteTrips().isEmpty()) {
                TripDataFetcher.getJSONHomeTripData(model.getAppData().getFavouriteTrips().get(0).getStartLocationID(), model.getAppData().getFavouriteTrips().get(0).getEndLocationID(), getView().getContext(), model);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public Context getViewContext() {
        return getView().getContext();
    }
}