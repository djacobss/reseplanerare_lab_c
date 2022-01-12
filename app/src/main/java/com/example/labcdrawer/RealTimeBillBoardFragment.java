package com.example.labcdrawer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

public class RealTimeBillBoardFragment extends Fragment {


    private static final String STATION_LIST = "stationList";

    private ArrayList<Integer> stationList;
    private Model model;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
            setHasOptionsMenu(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_real_time_bill_board, container, false);
        model.setBillBoardFragment(this);
        recyclerView = view.findViewById(R.id.realTimeBillboardRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        if (!model.getAppData().getFavouriteStations().isEmpty()) {
            BillboardDataFetcher.getBillboardJSONData(model.getAppData().getFavouriteStations().get(0).getSiteIDString(), view.getContext(), model);
        }
        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void showResults(ArrayList<BillboardItem> billboardItems) {
        adapter = new RecyclerBillboardAdapter(billboardItems, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.transport_mode_realtime_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!model.getAppData().getFavouriteStations().isEmpty()) {
            switch (item.getItemId()) {
                case R.id.realTimeUpdateItem:
                    BillboardDataFetcher.getBillboardJSONData(model.getAppData().getFavouriteStations().get(0).getSiteIDString(), getView().getContext(), model);
                    break;
                case R.id.transportModeBusItem:
                    model.getAppData().setRealTimeTransportMode(TransportMode.BUS);
                    BillboardDataFetcher.getBillboardJSONData(model.getAppData().getFavouriteStations().get(0).getSiteIDString(), getView().getContext(), model);
                    break;
                case R.id.transportModeMetroItem:
                    model.getAppData().setRealTimeTransportMode(TransportMode.METRO);
                    BillboardDataFetcher.getBillboardJSONData(model.getAppData().getFavouriteStations().get(0).getSiteIDString(), getView().getContext(), model);
                    break;
                case R.id.transportModeTrainItem:
                    model.getAppData().setRealTimeTransportMode(TransportMode.TRAIN);
                    BillboardDataFetcher.getBillboardJSONData(model.getAppData().getFavouriteStations().get(0).getSiteIDString(), getView().getContext(), model);
                    break;
                case R.id.transportModeTramItem:
                    model.getAppData().setRealTimeTransportMode(TransportMode.TRAM);
                    BillboardDataFetcher.getBillboardJSONData(model.getAppData().getFavouriteStations().get(0).getSiteIDString(), getView().getContext(), model);
                    break;
                case R.id.transportModeShipItem:
                    model.getAppData().setRealTimeTransportMode(TransportMode.SHIP);
                    BillboardDataFetcher.getBillboardJSONData(model.getAppData().getFavouriteStations().get(0).getSiteIDString(), getView().getContext(), model);
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}