package com.example.labcdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class StationRealTimeActivity extends AppCompatActivity {

    private LocationItem currentItem;
    private AppData appData;
    private Toolbar toolbar;
    private Model model;
    private ReturnToFragment returnToFragment;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView lastUpdated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_real_time);


        model = new Model();
        if ((LocationItem) getIntent().getSerializableExtra("Item") != null) {
            currentItem = (LocationItem) getIntent().getSerializableExtra("Item");
            appData = (AppData) getIntent().getSerializableExtra("AppData");
            returnToFragment = (ReturnToFragment) getIntent().getSerializableExtra("Fragment");
            model.setAppData(appData);
            model.setStationRealTimeActivity(this);
        } else {
            appData = new AppData();
            returnToFragment = ReturnToFragment.REALTIME_SEARCH;
        }
        lastUpdated = findViewById(R.id.realTimeStationLastUpdatedText);

        toolbar = findViewById(R.id.toolbarStationRealTime);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(currentItem.getPlaceName());
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_backaction));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });
        RealTimeDataFetcher.getJSONRealTimeData(Integer.toString(currentItem.getSiteID()), this, model);
        recyclerView = findViewById(R.id.realTimeRecyclerViewStation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.transport_mode_realtime_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.realTimeUpdateItem:
                RealTimeDataFetcher.getJSONRealTimeData(Integer.toString(currentItem.getSiteID()), this, model);
            case R.id.transportModeBusItem:
                model.getAppData().setRealTimeTransportMode(TransportMode.BUS);
                RealTimeDataFetcher.getJSONRealTimeData(Integer.toString(currentItem.getSiteID()), this, model);
                break;
            case R.id.transportModeMetroItem:
                model.getAppData().setRealTimeTransportMode(TransportMode.METRO);
                RealTimeDataFetcher.getJSONRealTimeData(Integer.toString(currentItem.getSiteID()), this, model);
                break;
            case R.id.transportModeTrainItem:
                model.getAppData().setRealTimeTransportMode(TransportMode.TRAIN);
                RealTimeDataFetcher.getJSONRealTimeData(Integer.toString(currentItem.getSiteID()), this, model);
                break;
            case R.id.transportModeTramItem:
                model.getAppData().setRealTimeTransportMode(TransportMode.TRAM);
                RealTimeDataFetcher.getJSONRealTimeData(Integer.toString(currentItem.getSiteID()), this, model);
                break;
            case R.id.transportModeShipItem:
                model.getAppData().setRealTimeTransportMode(TransportMode.SHIP);
                RealTimeDataFetcher.getJSONRealTimeData(Integer.toString(currentItem.getSiteID()), this, model);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        returnToMain();
    }

    private void returnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Fragment", (Serializable) returnToFragment);
        intent.putExtra("AppData", (Serializable) appData);
        startActivity(intent);
    }

    public void showResults(ArrayList<RealTimeItem> responseList) {
        lastUpdated.setText(model.getLastUpdatedRealTime());
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerStationAdapter(responseList, this);
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        model.saveFromStationActivity();
        super.onPause();
    }

    public void showTimeout() {
        Toast.makeText(this,"Timeout Error", Toast.LENGTH_LONG).show();
    }
}
