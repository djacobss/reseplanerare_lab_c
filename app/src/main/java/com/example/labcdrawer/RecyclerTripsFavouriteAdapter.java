package com.example.labcdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerTripsFavouriteAdapter extends RecyclerView.Adapter<RecyclerTripsFavouriteAdapter.RecyclerTripsFavouriteVH> {

    private ArrayList<TripItem> itemArrayList;

    public static class RecyclerTripsFavouriteVH extends RecyclerView.ViewHolder {

        private ImageView removeImage;
        private TextView startLocation, endLocation;

        public RecyclerTripsFavouriteVH(@NonNull View itemView) {
            super(itemView);
            removeImage = itemView.findViewById(R.id.tripsFavouriteRemoveImage);
            startLocation = itemView.findViewById(R.id.tripsFavouriteStartLocation);
            endLocation = itemView.findViewById(R.id.tripsFavouriteEndLocation);
        }

        public ImageView getRemoveImage() {
            return removeImage;
        }

        public TextView getStartLocation() {
            return startLocation;
        }

        public TextView getEndLocation() {
            return endLocation;
        }
    }

    public RecyclerTripsFavouriteAdapter(ArrayList<TripItem> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public RecyclerTripsFavouriteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerTripsFavouriteVH recyclerTripsFavouriteVH = new RecyclerTripsFavouriteVH(view);
        return recyclerTripsFavouriteVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTripsFavouriteVH holder, int position) {
        TripItem currentItem = itemArrayList.get(position);
        holder.getStartLocation().setText(currentItem.getStartLocationName());
        holder.getEndLocation().setText(currentItem.getEndLocationName());
        holder.getRemoveImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemArrayList.remove(currentItem);
                notifyItemRemoved(holder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.trips_favourite_item;
    }

}
