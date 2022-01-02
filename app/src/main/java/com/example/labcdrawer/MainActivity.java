package com.example.labcdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

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

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RealTimeBillBoardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_realTime_BillBoard);
        }
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
                RealTimeBillBoardFragment realTimeBillBoardFragment = RealTimeBillBoardFragment.newInstance(model.getFavouriteStationsStrings());
                FragmentManager fragmentManagerBillboard = getSupportFragmentManager();
                FragmentTransaction transactionBillboard = fragmentManagerBillboard.beginTransaction();
                transactionBillboard.replace(R.id.fragment_container, realTimeBillBoardFragment, "REALTIME_BILLBOARD_FRAGMENT").commit();
                break;
            case R.id.nav_realTime_Search:
                RealTimeSearchFragment realTimeSearchFragment = RealTimeSearchFragment.newInstance(model.getFavouriteStationsStrings());
                FragmentManager fragmentManagerSearchRT = getSupportFragmentManager();
                FragmentTransaction transactionSearchRT = fragmentManagerSearchRT.beginTransaction();
                transactionSearchRT.replace(R.id.fragment_container, realTimeSearchFragment, "REALTIME_SEARCH_FRAGMENT").commit();
                break;
            case R.id.nav_realTime_Favourites:
                RealTimeFavouriteFragment realTimeFavouriteFragment = RealTimeFavouriteFragment.newInstance(model.getFavouriteStationsStrings());
                FragmentManager fragmentManagerFavRT = getSupportFragmentManager();
                FragmentTransaction transactionFavRT = fragmentManagerFavRT.beginTransaction();
                transactionFavRT.replace(R.id.fragment_container, realTimeFavouriteFragment, "REALTIME_FAVOURITE_FRAGMENT").commit();
                break;
            case R.id.nav_trips_showFavs:
                TripsHomeFragment tripsHomeFragment = TripsHomeFragment.newInstance(model.getFavouriteTripsStartStrings(),model.getFavouriteTripsEndStrings());
                FragmentManager fragmentManagerHomeT = getSupportFragmentManager();
                FragmentTransaction transactionHomeT = fragmentManagerHomeT.beginTransaction();
                transactionHomeT.replace(R.id.fragment_container, tripsHomeFragment, "TRIPS_HOME_FRAGMENT").commit();
                break;
            case R.id.nav_trips_search:
                TripsSearchFragment tripsSearchFragment = TripsSearchFragment.newInstance(model.getFavouriteTripsStartStrings(),model.getFavouriteTripsEndStrings());
                FragmentManager fragmentManagerSearchT = getSupportFragmentManager();
                FragmentTransaction transactionSearchT = fragmentManagerSearchT.beginTransaction();
                transactionSearchT.replace(R.id.fragment_container, tripsSearchFragment, "TRIPS_SEARCH_FRAGMENT").commit();
                break;
            case R.id.nav_trips_Favourites:
                TripsFavouriteFragment tripsFavouriteFragment = TripsFavouriteFragment.newInstance(model.getFavouriteTripsStartStrings(),model.getFavouriteTripsEndStrings());
                FragmentManager fragmentManagerFavT = getSupportFragmentManager();
                FragmentTransaction transactionFavT = fragmentManagerFavT.beginTransaction();
                transactionFavT.replace(R.id.fragment_container, tripsFavouriteFragment, "TRIPS_FAVOURITE_FRAGMENT").commit();
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
}