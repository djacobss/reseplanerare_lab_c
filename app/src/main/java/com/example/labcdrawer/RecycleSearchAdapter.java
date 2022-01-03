package com.example.labcdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleSearchAdapter extends RecyclerView.Adapter<RecycleSearchAdapter.RecycleAdapterViewHolder> {

    private ArrayList<LocationItem> itemArrayList;
    private ItemClickListenerFav itemClickListenerFav;
    private ItemClickListenerName itemClickListenerName;
    private Context context;

    public static class RecycleAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView searchResultTextView;
        private ImageView searchResultFavouriteImageView;


        public RecycleAdapterViewHolder(View itemView) {
            super(itemView);
            searchResultTextView = itemView.findViewById(R.id.realTimeSearchCardViewText);
            searchResultFavouriteImageView = itemView.findViewById(R.id.realTimeSearchFavouriteImageView);
        }

        public TextView getSearchResultTextView() {
            return searchResultTextView;
        }

        public ImageView getSearchResultFavouriteImageView() {
            return searchResultFavouriteImageView;
        }

    }

    public RecycleSearchAdapter(ArrayList<LocationItem> itemArrayList, Context context, ItemClickListenerFav itemClickListenerFav, ItemClickListenerName itemClickListenerName) {
        this.itemArrayList = itemArrayList;
        this.context = context;
        this.itemClickListenerFav = itemClickListenerFav;
        this.itemClickListenerName = itemClickListenerName;
    }

    @NonNull
    @Override
    public RecycleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecycleAdapterViewHolder recycleAdapterViewHolder = new RecycleAdapterViewHolder(view);
        return recycleAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterViewHolder holder, int position) {
        LocationItem currentItem = itemArrayList.get(position);
        holder.getSearchResultTextView().setText(currentItem.getPlaceName());
        if (currentItem.getFavourite()) {
            holder.getSearchResultFavouriteImageView().setImageResource(R.drawable.ic_favourite_true);
        } else {
            holder.getSearchResultFavouriteImageView().setImageResource(R.drawable.ic_favourite_false);
        }
        int pos = position;
        holder.getSearchResultFavouriteImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListenerFav != null) {
                    if (currentItem.getFavourite()) {
                        holder.getSearchResultFavouriteImageView().setImageResource(R.drawable.ic_favourite_false);
                        currentItem.setFavourite(false);
                    } else {
                        holder.getSearchResultFavouriteImageView().setImageResource(R.drawable.ic_favourite_true);
                        currentItem.setFavourite(true);
                    }
                    itemClickListenerFav.onItemFavClick(currentItem);
                }
            }
        });
        holder.getSearchResultTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListenerName != null) {
                    itemClickListenerName.onItemNameClick(currentItem);
                }
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

    public interface ItemClickListenerFav {
        void onItemFavClick(LocationItem locationItem);
    }

    public interface ItemClickListenerName {
        void onItemNameClick(LocationItem locationItem);
    }


}
