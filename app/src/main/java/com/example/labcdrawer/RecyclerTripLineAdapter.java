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

public class RecyclerTripLineAdapter extends RecyclerView.Adapter<RecyclerTripLineAdapter.RecyclerTripLineViewHolder> {

    private ArrayList<TripLineItem> itemArrayList;
    private Context context;

    public static class RecyclerTripLineViewHolder extends RecyclerView.ViewHolder {

        private TextView lineText;
        private ImageView connectImage, travelModeImage;

        public RecyclerTripLineViewHolder(@NonNull View itemView) {
            super(itemView);
            lineText = itemView.findViewById(R.id.tripsSearchItemLineSubItem);
            connectImage = itemView.findViewById(R.id.tripsSearchItemImageSubItem);
            travelModeImage = itemView.findViewById(R.id.tripsLineItemImage);
        }

        public TextView getLineText() {
            return lineText;
        }

        public ImageView getConnectImage() {
            return connectImage;
        }

        public ImageView getTravelModeImage() {
            return travelModeImage;
        }
    }

    public RecyclerTripLineAdapter(ArrayList<TripLineItem> itemArrayList, Context context){
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerTripLineAdapter.RecyclerTripLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerTripLineViewHolder recyclerTripLineViewHolder = new RecyclerTripLineViewHolder(view);
        return recyclerTripLineViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTripLineAdapter.RecyclerTripLineViewHolder holder, int position) {
        TripLineItem currentItem = itemArrayList.get(position);
        holder.getLineText().setText(currentItem.getLine());
        holder.getTravelModeImage().setImageResource(getDrawableFromMode(currentItem.getTransportMode()));
        if(position == itemArrayList.size()-1){
            holder.getConnectImage().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.line_list_trips_search_item;
    }

    private int getDrawableFromMode(TransportMode transportMode){
        switch (transportMode){
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
