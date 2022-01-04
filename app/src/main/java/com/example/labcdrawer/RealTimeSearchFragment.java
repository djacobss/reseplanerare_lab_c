package com.example.labcdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class RealTimeSearchFragment extends Fragment {

    private static final String STATION_LIST = "stationList";

    private ArrayList<Integer> stationList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<LocationItem> locationItems;
    private View mainView;
    private EditText searchBar;
    private Button searchBtn;
    private ImageView itemFavouriteBtn;
    private Model model;
    private boolean searchFromStart;

    public RealTimeSearchFragment() {
        searchFromStart = false;
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
        locationItems = new ArrayList<>();
        recyclerView = view.findViewById(R.id.realTimeSearchRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new RecycleSearchAdapter(locationItems, getContext(), new RecycleSearchAdapter.ItemClickListenerFav() {
            @Override
            public void onItemFavClick(LocationItem locationItem) {
                if (locationItem.getFavourite()) {
                    model.addFavouriteStation(locationItem);
                } else {
                    model.removeFavouriteStation(locationItem);
                }
            }
        }, new RecycleSearchAdapter.ItemClickListenerName() {
            @Override
            public void onItemNameClick(LocationItem locationItem) {
                Log.e("Test: ", "In click");
                Intent intent = new Intent(getActivity(),StationRealTimeActivity.class);
                intent.putExtra("Item",(Serializable) locationItem);
                intent.putExtra("AppData",(Serializable) model.getAppData());
                intent.putExtra("Fragment",(Serializable) ReturnToFragment.REALTIME_SEARCH);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        searchBar = view.findViewById(R.id.realTimeSearchEditText);
        searchBtn = view.findViewById(R.id.realTimeSearchButton);
        itemFavouriteBtn = view.findViewById(R.id.realTimeSearchFavouriteImageView);
        model.setRealTimeSearchFragment(this);
        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (searchBar.getText().toString().length() == 0) {
                        searchBar.setError("Ingen text angiven");
                        return false;
                    } else {
                        model.getAppData().setLastSearchString(searchBar.getText().toString());
                        StationDataFetcher.getJSONStationData(searchBar.getText().toString(), view.getContext(), model);
                        return true;
                    }
                }
                return false;
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchBar.getText().toString().length() == 0) {
                    searchBar.setError("Ingen text angiven");
                } else {
                    model.getAppData().setLastSearchString(searchBar.getText().toString());
                    StationDataFetcher.getJSONStationData(searchBar.getText().toString(), view.getContext(), model);
                }
            }
        });
        if(searchFromStart){
            StationDataFetcher.getJSONStationData(model.getAppData().getLastSearchString(),view.getContext(),model);
        }
        return view;
    }

    public View getMainView() {
        return mainView;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void searchTimeOutError() {
        Toast.makeText(getContext(), "Ingen Anslutning", Toast.LENGTH_LONG).show();
    }

    public void wrongInputError() {
        Toast.makeText(getContext(), "Inga Resultat", Toast.LENGTH_LONG).show();
    }

    public void showResults(ArrayList<LocationItem> searchResultList) {
        locationItems.clear();
        locationItems.addAll(searchResultList);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecycleSearchAdapter(locationItems, getContext(), new RecycleSearchAdapter.ItemClickListenerFav() {
            @Override
            public void onItemFavClick(LocationItem locationItem) {
                if (locationItem.getFavourite()) {
                    model.addFavouriteStation(locationItem);
                } else {
                    model.removeFavouriteStation(locationItem);
                }
            }
        }, new RecycleSearchAdapter.ItemClickListenerName() {
            @Override
            public void onItemNameClick(LocationItem locationItem) {
                Intent intent = new Intent(getActivity(),StationRealTimeActivity.class);
                intent.putExtra("Item",(Serializable) locationItem);
                intent.putExtra("AppData",(Serializable) model.getAppData());
                intent.putExtra("Fragment",(Serializable) ReturnToFragment.REALTIME_SEARCH);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    public void setSearchFromStart(boolean searchFromStart) {
        this.searchFromStart = searchFromStart;
    }
}