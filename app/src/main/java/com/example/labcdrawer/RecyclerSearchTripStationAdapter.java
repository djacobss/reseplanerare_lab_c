package com.example.labcdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerSearchTripStationAdapter extends RecyclerView.Adapter<RecyclerSearchTripStationAdapter.RecyclerSearchTripStationVH> {

    private ArrayList<LocationItem> itemArrayList;
    private OnStationTripClickListener clickListener;

    public static class RecyclerSearchTripStationVH extends RecyclerView.ViewHolder {

        private TextView searchCardText;
        private CardView cardView;

        public RecyclerSearchTripStationVH(@NonNull View itemView) {
            super(itemView);
            searchCardText = itemView.findViewById(R.id.tripsSearchCardViewText);
            cardView = itemView.findViewById(R.id.tripsSearchLocationItemCard);
        }

        public TextView getSearchCardText() {
            return searchCardText;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public RecyclerSearchTripStationAdapter(ArrayList<LocationItem> itemArrayList, OnStationTripClickListener clickListener) {
        this.itemArrayList = itemArrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerSearchTripStationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerSearchTripStationVH recyclerSearchTripStationVH = new RecyclerSearchTripStationVH(view);
        return recyclerSearchTripStationVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerSearchTripStationVH holder, int position) {
        LocationItem currentItem = itemArrayList.get(position);
        holder.getSearchCardText().setText(currentItem.getPlaceName());
        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onStationTripClick(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.trips_search_location_item;
    }

    public interface OnStationTripClickListener {
        void onStationTripClick(LocationItem locationItem);
    }
}
