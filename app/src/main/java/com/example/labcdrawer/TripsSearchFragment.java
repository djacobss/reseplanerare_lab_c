package com.example.labcdrawer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TripsSearchFragment extends Fragment {

    private EditText startSearchEditText, endSearchEditText, datePickEditText;
    private TextView startChoiceText, endChoiceText;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RadioGroup radioGroup;
    private Model model;
    private Button searchBtn, addFavouriteBtn, searchTripsBtn;
    private String startSearchString, endSearchString, datePicked, timePicked;
    private int noOfLocationsPicked;
    private ConstraintLayout expandableLayout;
    private LocationItem firstLocation,endLocation;

    public TripsSearchFragment() {

    }

    public static TripsSearchFragment newInstance() {
        TripsSearchFragment fragment = new TripsSearchFragment();
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
        noOfLocationsPicked = 0;
        model.setTimeChoice(TripsTimeChoice.NOW);
        View view = inflater.inflate(R.layout.fragment_trips_search, container, false);

        startSearchEditText = view.findViewById(R.id.tripsSearchStart);
        endSearchEditText = view.findViewById(R.id.tripsSearchEnd);
        searchBtn = view.findViewById(R.id.tripsSearchBtn);
        radioGroup = view.findViewById(R.id.tripsSearchRadioGroup);
        datePickEditText = view.findViewById(R.id.tripsSearchChooseTime);
        startChoiceText = view.findViewById(R.id.tripsSearchStartText);
        endChoiceText = view.findViewById(R.id.tripsSearchEndText);
        recyclerView = view.findViewById(R.id.tripsSearchRecyclerView);
        expandableLayout = view.findViewById(R.id.tripsAddFavouriteTripExpandable);
        addFavouriteBtn = view.findViewById(R.id.tripsAddFavouriteButton);
        searchTripsBtn = view.findViewById(R.id.tripsActualSearchBtn);

        endSearchEditText.setEnabled(false);
        startSearchEditText.setEnabled(true);
        model.setTripsSearchFragment(this);
        datePickEditText.setInputType(InputType.TYPE_NULL);
        datePickEditText.setEnabled(false);
        model.setCurrentFragment(ReturnToFragment.TRIPS_SEARCH);
        expandableLayout.setVisibility(View.GONE);
        radioGroup.check(R.id.tripsSearchRadio1);
        datePicked = null;
        timePicked = null;

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startSearchEditText.getText().toString().length() > 0) {
                    startSearchString = startSearchEditText.getText().toString();
                    TripsStationDataFetcher.getJSONStationData(startSearchString, view.getContext(), model);
                }
            }
        });
        datePickEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tripsSearchRadio1:
                        radioGroup.check(R.id.tripsSearchRadio1);
                        model.setTimeChoice(TripsTimeChoice.NOW);
                        break;
                    case R.id.tripsSearchRadio2:
                        radioGroup.check(R.id.tripsSearchRadio2);
                        model.setTimeChoice(TripsTimeChoice.TRAVEL_AT);
                        datePickEditText.setEnabled(true);
                        break;
                    case R.id.tripsSearchRadio3:
                        radioGroup.check(R.id.tripsSearchRadio3);
                        model.setTimeChoice(TripsTimeChoice.ARRIVE_AT);
                        datePickEditText.setEnabled(true);
                        break;
                }
            }
        });
        searchTripsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"Ange hållplatser innan sökning", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void showStationResults(ArrayList<LocationItem> searchResultList) {
        layoutManager = new LinearLayoutManager(getView().getContext());
        adapter = new RecyclerSearchTripStationAdapter(searchResultList, new RecyclerSearchTripStationAdapter.OnStationTripClickListener() {
            @Override
            public void onStationTripClick(LocationItem locationItem) {
                if (noOfLocationsPicked == 0) {
                    noOfLocationsPicked++;
                    firstLocation = locationItem;
                    startChoiceText.setText(locationItem.getPlaceName());
                    endSearchEditText.setEnabled(true);
                    startSearchEditText.setEnabled(false);
                    searchBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (endSearchEditText.getText().toString().length() > 0) {
                                endSearchString = endSearchEditText.getText().toString();
                                TripsStationDataFetcher.getJSONStationData(endSearchString, getView().getContext(), model);
                            }
                        }
                    });
                } else if (noOfLocationsPicked == 1) {
                    if (!firstLocation.getPlaceName().equals(locationItem.getPlaceName())) {
                        expandableLayout.setVisibility(View.VISIBLE);
                        noOfLocationsPicked++;
                        endChoiceText.setText(locationItem.getPlaceName());
                        endLocation = locationItem;
                        addFavouriteBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TripItem tempTripItem = new TripItem();
                                tempTripItem.setStartLocationName(firstLocation.getPlaceName());
                                tempTripItem.setStartLocationID(firstLocation.getSiteIDString());
                                tempTripItem.setEndLocationName(endLocation.getPlaceName());
                                tempTripItem.setEndLocationID(endLocation.getSiteIDString());

                                model.setCurrentStartLocID(firstLocation.getSiteIDString());
                                model.setCurrentEndLocID(endLocation.getSiteIDString());
                                tempTripItem.setFavourite(true);
                                boolean favouriteExists = false;
                                for (TripItem item : model.getAppData().getFavouriteTrips()) {
                                    if(item.getStartLocationName().equals(tempTripItem.getStartLocationName()) && item.getEndLocationName().equals(tempTripItem.getEndLocationName())){
                                        favouriteExists = true;
                                    }
                                }
                                if(!favouriteExists) {
                                    model.getAppData().getFavouriteTrips().add(tempTripItem);
                                } else {
                                    Toast.makeText(getView().getContext(),"Favorit finns redan", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        endSearchEditText.setEnabled(false);
                        searchBtn.setCompoundDrawables(getView().getContext().getResources().getDrawable(R.drawable.ic_backarrow), null, null, null);
                        searchBtn.setText("Sök Igen");
                        searchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                searchAgain();
                            }
                        });
                        searchTripsBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(model.getTimeChoice().equals(TripsTimeChoice.NOW)){
                                    TripDataFetcher.getJSONTripData(firstLocation.getSiteIDString(),endLocation.getSiteIDString(),getView().getContext(),model);
                                } else if(model.getTimeChoice().equals(TripsTimeChoice.ARRIVE_AT) || model.getTimeChoice().equals(TripsTimeChoice.TRAVEL_AT)){
                                    if(datePicked!=null && timePicked != null){
                                        TripDataFetcher.getJSONTripData(firstLocation.getSiteIDString(),endLocation.getSiteIDString(),getView().getContext(),model);
                                    } else {
                                        Toast.makeText(getView().getContext(),"Ange önskad tid först",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getView().getContext(), "Välj en annan station", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void searchAgain() {
        expandableLayout.setVisibility(View.GONE);
        noOfLocationsPicked = 0;
        startSearchEditText.setText("");
        endSearchEditText.setText("");
        endSearchEditText.setEnabled(false);
        startSearchEditText.setEnabled(true);
        startChoiceText.setText("Sök för att välja");
        endChoiceText.setText("Sök för att välja");
        searchBtn.setCompoundDrawables(getView().getContext().getResources().getDrawable(R.drawable.ic_baseline_search_24), null, null, null);
        searchBtn.setText("Sök");
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startSearchEditText.getText().toString().length() > 0) {
                    startSearchString = startSearchEditText.getText().toString();
                    TripsStationDataFetcher.getJSONStationData(startSearchString, getView().getContext(), model);
                }
            }
        });
    }

    public void showDateTimeDialog() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        String fullDate = simpleDateFormat.format(calendar.getTime());
                        datePicked = fullDate.substring(0, 10);
                        timePicked = fullDate.substring(11, 16);

                        model.setDesiredTime(timePicked);
                        model.setDesiredDate(datePicked);

                        datePickEditText.setText(fullDate);
                    }
                };
                TimePickerDialog timePicker = new TimePickerDialog(getView().getContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePicker.show();
            }
        };
        DatePickerDialog datePicker = new DatePickerDialog(getView().getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    public void showTripsResults(ArrayList<TripItem> tripItems) {
        layoutManager = new LinearLayoutManager(getView().getContext());
        adapter = new RecyclerTripsAdapter(tripItems,getView().getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}