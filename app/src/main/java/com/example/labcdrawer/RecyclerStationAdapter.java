package com.example.labcdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerStationAdapter extends RecyclerView.Adapter<RecyclerStationAdapter.RecyclerStationViewHolder> {

    private ArrayList<RealTimeItem> realTimeItems;
    private Context context;

    public static class RecyclerStationViewHolder extends RecyclerView.ViewHolder{

        private TextView lineTextView;
        private TextView destinationTextView;
        private TextView timeTextView;

        public RecyclerStationViewHolder(@NonNull View itemView) {
            super(itemView);
            lineTextView = itemView.findViewById(R.id.realTimeStationBusLine);
            destinationTextView = itemView.findViewById(R.id.realTimeStationDestText);
            timeTextView = itemView.findViewById(R.id.realTimeStationTime);
        }

        public TextView getLineTextView() {
            return lineTextView;
        }

        public TextView getDestinationTextView() {
            return destinationTextView;
        }

        public TextView getTimeTextView() {
            return timeTextView;
        }
    }

    public RecyclerStationAdapter(ArrayList<RealTimeItem> realTimeItems, Context context) {
        this.realTimeItems = realTimeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerStationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        RecyclerStationViewHolder recyclerStationViewHolder = new RecyclerStationViewHolder(view);
        return recyclerStationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerStationViewHolder holder, int position) {
        RealTimeItem currentItem = realTimeItems.get(position);
        holder.getLineTextView().setText(currentItem.getLineNumberString());
        holder.getDestinationTextView().setText(currentItem.getBusDestination());
        holder.getTimeTextView().setText(currentItem.getTimeLeft());
    }

    @Override
    public int getItemCount() {
        return realTimeItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.real_time_station_item;
    }
}
