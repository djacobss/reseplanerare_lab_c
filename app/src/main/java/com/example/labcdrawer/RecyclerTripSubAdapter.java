package com.example.labcdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerTripSubAdapter extends RecyclerView.Adapter<RecyclerTripSubAdapter.RecyclerTripSubViewHolder> {

    private ArrayList<TripSubItem> itemArrayList;
    private Context context;

    public static class RecyclerTripSubViewHolder extends RecyclerView.ViewHolder {

        private TextView startStation, endStation, startTime, endTime, direction, lineText;
        private ImageView travelModeImage;

        public RecyclerTripSubViewHolder(@NonNull View itemView) {
            super(itemView);
            startStation = itemView.findViewById(R.id.tripsSubItemFirstLoc);
            endStation = itemView.findViewById(R.id.tripsSubItemSecondLoc);
            startTime = itemView.findViewById(R.id.tripsSubItemFirstTime);
            endTime = itemView.findViewById(R.id.tripsSubItemSecondTime);
            lineText = itemView.findViewById(R.id.tripsSubItemLineBetween);
            direction = itemView.findViewById(R.id.tripsSibItemLineNameBetween);
            travelModeImage = itemView.findViewById(R.id.tripsImageS2SLine);
        }

        public TextView getStartStation() {
            return startStation;
        }

        public TextView getEndStation() {
            return endStation;
        }

        public TextView getStartTime() {
            return startTime;
        }

        public TextView getEndTime() {
            return endTime;
        }

        public TextView getDirection() {
            return direction;
        }

        public TextView getLineText() {
            return lineText;
        }

        public ImageView getTravelModeImage() {
            return travelModeImage;
        }
    }

    public RecyclerTripSubAdapter(ArrayList<TripSubItem> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerTripSubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerTripSubViewHolder recyclerTripSubViewHolder = new RecyclerTripSubViewHolder(view);
        return recyclerTripSubViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTripSubViewHolder holder, int position) {
        TripSubItem currentItem = itemArrayList.get(position);
        holder.getStartStation().setText(currentItem.getStartStation());
        holder.getEndStation().setText(currentItem.getEndStation());
        holder.getStartTime().setText(currentItem.getStartTime());
        holder.getEndTime().setText(currentItem.getEndTime());
        holder.getDirection().setText(currentItem.getDirection());
        holder.getLineText().setText(currentItem.getLine());
        holder.getTravelModeImage().setImageResource(getImageIDFromItem(currentItem));
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.station_to_station_item;
    }

    private int getImageIDFromItem(TripSubItem tripSubItem) {
        switch (tripSubItem.getTransportMode()) {
            case BUS:
                return R.drawable.ic_bustravel;
            case TRAIN:
                return R.drawable.ic_traintravel;
            case TRAM:
                return R.drawable.ic_tramtravel;
            case SHIP:
                return R.drawable.ic_shiptravel;
            case METRO:
                return R.drawable.ic_metrotravel;
            case WALK:
                return R.drawable.ic_walktravel;
        }
        return R.drawable.ic_bustravel;
    }
}
