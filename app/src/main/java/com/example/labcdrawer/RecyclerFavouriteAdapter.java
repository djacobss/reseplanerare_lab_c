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

public class RecyclerFavouriteAdapter extends RecyclerView.Adapter<RecyclerFavouriteAdapter.RecyclerFavouriteAdapterViewHolder> {

    private ArrayList<LocationItem> itemArrayList;
    private Context context;
    private FavItemClickListener favItemClickListener;

    public static class RecyclerFavouriteAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private ImageView favouriteImageView;

        public RecyclerFavouriteAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.realTimeSearchCardViewText);
            favouriteImageView = itemView.findViewById(R.id.realTimeSearchFavouriteImageView);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public ImageView getFavouriteImageView() {
            return favouriteImageView;
        }
    }


    public RecyclerFavouriteAdapter(ArrayList<LocationItem> itemArrayList, Context context, FavItemClickListener favItemClickListener){
        this.itemArrayList = itemArrayList;
        this.context = context;
        this.favItemClickListener = favItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerFavouriteAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerFavouriteAdapterViewHolder recyclerFavouriteAdapterViewHolder = new RecyclerFavouriteAdapterViewHolder(view);
        return recyclerFavouriteAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerFavouriteAdapterViewHolder holder, int position) {
        LocationItem currentItem = itemArrayList.get(position);
        holder.getNameTextView().setText(currentItem.getPlaceName());
        holder.getFavouriteImageView().setImageResource(R.drawable.ic_favourite_true);
        int pos = position;
        holder.getFavouriteImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemArrayList.get(pos).getFavourite()) {
                    holder.getFavouriteImageView().setImageResource(R.drawable.ic_favourite_false);
                    currentItem.setFavourite(false);
                    itemArrayList.get(pos).setFavourite(false);
                } else {
                    holder.getFavouriteImageView().setImageResource(R.drawable.ic_favourite_true);
                    currentItem.setFavourite(true);
                    itemArrayList.get(pos).setFavourite(true);
                }
                favItemClickListener.onFavItemClicked(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.example_item;
    }

    public interface FavItemClickListener{
        void onFavItemClicked(LocationItem locationItem);
    }
}
