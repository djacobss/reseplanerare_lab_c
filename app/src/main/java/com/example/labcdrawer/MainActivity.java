package com.example.labcdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Menu navigationMenu;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
        model = new Model();
        AppData appData = new AppData();
        model.setAppData(appData);
        model.setMainActivity(this);

        Intent intent = getIntent();
        if ((AppData) intent.getSerializableExtra("AppData") != null) {
            model.setAppData((AppData) intent.getSerializableExtra("AppData"));
            returnToFragment();
        }

        if (savedInstanceState == null && intent.getSerializableExtra("AppData") == null) {
            model.load();
            RealTimeBillBoardFragment realTimeBillBoardFragment = RealTimeBillBoardFragment.newInstance(model.getFavouriteStationInts());
            realTimeBillBoardFragment.setModel(model);
            FragmentManager fragmentManagerBillboard = getSupportFragmentManager();
            FragmentTransaction transactionBillboard = fragmentManagerBillboard.beginTransaction();
            transactionBillboard.replace(R.id.fragment_container, realTimeBillBoardFragment, "REALTIME_BILLBOARD_FRAGMENT").commit();
            navigationView.setCheckedItem(R.id.nav_realTime_BillBoard);
        }

    }

    @Override
    protected void onStart() {
        if (model.load()) {

        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        if (!isConnected()) {
            connectionErrorPopup();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        model.save();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_realTime_BillBoard:
                RealTimeBillBoardFragment realTimeBillBoardFragment = RealTimeBillBoardFragment.newInstance(model.getFavouriteStationInts());
                realTimeBillBoardFragment.setModel(model);
                FragmentManager fragmentManagerBillboard = getSupportFragmentManager();
                FragmentTransaction transactionBillboard = fragmentManagerBillboard.beginTransaction();
                transactionBillboard.replace(R.id.fragment_container, realTimeBillBoardFragment, "REALTIME_BILLBOARD_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_realTime_BillBoard);
                break;
            case R.id.nav_realTime_Search:
                RealTimeSearchFragment realTimeSearchFragment = RealTimeSearchFragment.newInstance(model.getFavouriteStationInts());
                realTimeSearchFragment.setModel(model);
                FragmentManager fragmentManagerSearchRT = getSupportFragmentManager();
                FragmentTransaction transactionSearchRT = fragmentManagerSearchRT.beginTransaction();
                transactionSearchRT.replace(R.id.fragment_container, realTimeSearchFragment, "REALTIME_SEARCH_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_realTime_Search);
                break;
            case R.id.nav_realTime_Favourites:
                RealTimeFavouriteFragment realTimeFavouriteFragment = RealTimeFavouriteFragment.newInstance(model.getFavouriteStationInts());
                realTimeFavouriteFragment.setModel(model);
                FragmentManager fragmentManagerFavRT = getSupportFragmentManager();
                FragmentTransaction transactionFavRT = fragmentManagerFavRT.beginTransaction();
                transactionFavRT.replace(R.id.fragment_container, realTimeFavouriteFragment, "REALTIME_FAVOURITE_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_realTime_Favourites);
                break;
            case R.id.nav_trips_showFavs:
                TripsHomeFragment tripsHomeFragment = TripsHomeFragment.newInstance();
                tripsHomeFragment.setModel(model);
                FragmentManager fragmentManagerHomeT = getSupportFragmentManager();
                FragmentTransaction transactionHomeT = fragmentManagerHomeT.beginTransaction();
                transactionHomeT.replace(R.id.fragment_container, tripsHomeFragment, "TRIPS_HOME_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_trips_showFavs);
                break;
            case R.id.nav_trips_search:
                TripsSearchFragment tripsSearchFragment = TripsSearchFragment.newInstance();
                tripsSearchFragment.setModel(model);
                FragmentManager fragmentManagerSearchT = getSupportFragmentManager();
                FragmentTransaction transactionSearchT = fragmentManagerSearchT.beginTransaction();
                transactionSearchT.replace(R.id.fragment_container, tripsSearchFragment, "TRIPS_SEARCH_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_trips_search);
                break;
            case R.id.nav_trips_Favourites:
                TripsFavouriteFragment tripsFavouriteFragment = TripsFavouriteFragment.newInstance();
                tripsFavouriteFragment.setModel(model);
                FragmentManager fragmentManagerFavT = getSupportFragmentManager();
                FragmentTransaction transactionFavT = fragmentManagerFavT.beginTransaction();
                transactionFavT.replace(R.id.fragment_container, tripsFavouriteFragment, "TRIPS_FAVOURITE_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_trips_Favourites);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void assignViews() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Reseplanerare");
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        colorTopMenuItems();

    }

    private void colorTopMenuItems() {

        navigationMenu = navigationView.getMenu();
        MenuItem realTimeMenuItem = navigationMenu.findItem(R.id.nav_realTime);
        MenuItem tripMenuItem = navigationMenu.findItem(R.id.nav_trips);
        SpannableString realTimeString = new SpannableString(realTimeMenuItem.getTitle());
        SpannableString tripString = new SpannableString(tripMenuItem.getTitle());
        realTimeString.setSpan(new TextAppearanceSpan(this, R.style.Theme_LabCDrawer_MyTextAppearance), 0, realTimeString.length(), 0);
        tripString.setSpan(new TextAppearanceSpan(this, R.style.Theme_LabCDrawer_MyTextAppearance), 0, tripString.length(), 0);
        realTimeMenuItem.setTitle(realTimeString);
        tripMenuItem.setTitle(tripString);

    }

    private void returnToFragment() {
        switch ((ReturnToFragment) getIntent().getSerializableExtra("Fragment")) {
            case REALTIME_BILLBOARD:
                RealTimeBillBoardFragment realTimeBillBoardFragment = RealTimeBillBoardFragment.newInstance(model.getFavouriteStationInts());
                realTimeBillBoardFragment.setModel(model);
                FragmentManager fragmentManagerBillboard = getSupportFragmentManager();
                FragmentTransaction transactionBillboard = fragmentManagerBillboard.beginTransaction();
                transactionBillboard.replace(R.id.fragment_container, realTimeBillBoardFragment, "REALTIME_BILLBOARD_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_realTime_BillBoard);
                break;
            case REALTIME_SEARCH:
                RealTimeSearchFragment realTimeSearchFragment = RealTimeSearchFragment.newInstance(model.getFavouriteStationInts());
                realTimeSearchFragment.setModel(model);
                realTimeSearchFragment.setSearchFromStart(true);
                FragmentManager fragmentManagerSearchRT = getSupportFragmentManager();
                FragmentTransaction transactionSearchRT = fragmentManagerSearchRT.beginTransaction();
                transactionSearchRT.replace(R.id.fragment_container, realTimeSearchFragment, "REALTIME_SEARCH_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_realTime_Search);
                break;
            case TRIPS_HOME:
                TripsHomeFragment tripsHomeFragment = TripsHomeFragment.newInstance();
                tripsHomeFragment.setModel(model);
                FragmentManager fragmentManagerHomeT = getSupportFragmentManager();
                FragmentTransaction transactionHomeT = fragmentManagerHomeT.beginTransaction();
                transactionHomeT.replace(R.id.fragment_container, tripsHomeFragment, "TRIPS_HOME_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_trips_showFavs);
                break;
            case TRIPS_SEARCH:
                TripsSearchFragment tripsSearchFragment = TripsSearchFragment.newInstance();
                tripsSearchFragment.setModel(model);
                FragmentManager fragmentManagerSearchT = getSupportFragmentManager();
                FragmentTransaction transactionSearchT = fragmentManagerSearchT.beginTransaction();
                transactionSearchT.replace(R.id.fragment_container, tripsSearchFragment, "TRIPS_SEARCH_FRAGMENT").commit();
                navigationView.setCheckedItem(R.id.nav_trips_search);
                break;
            default:
                break;
        }
    }

    public void showTimeoutToast() {
        Toast.makeText(this, "Timeout Error", Toast.LENGTH_LONG).show();
    }

    public void connectionErrorPopup() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Ingen Anslutning");
        alertDialogBuilder.setMessage("Koppla upp dig till internet innan du kan fortsätta");
        alertDialogBuilder.setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public boolean isConnected() {  //Checks for internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }
}