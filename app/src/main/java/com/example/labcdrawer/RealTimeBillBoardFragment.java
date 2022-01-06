package com.example.labcdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_real_time_bill_board, container, false);
        model.setBillBoardFragment(this);
        recyclerView = view.findViewById(R.id.realTimeBillboardRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        for (String s : model.getFavouriteStationStrings()) {
            Log.e("Test in fragment: ", s);
        }
        BillboardDataFetcher.getBillboardJSONData(model.getFavouriteStationStrings(),view.getContext(),model);
        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void showResults(ArrayList<BillboardItem> billboardItems){
        adapter = new RecyclerBillboardAdapter(billboardItems, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}