package com.example.labcdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleSearchAdapter extends RecyclerView.Adapter<RecycleSearchAdapter.RecycleAdapterViewHolder> {

    public ArrayList<SearchRecyclerItem> mItemListArray;

    public static class RecycleAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView searchResultImageView;

        public RecycleAdapterViewHolder(View itemView){
            super(itemView);
            searchResultImageView = itemView.findViewById(R.id.realTimeSearchCardViewText);
        }

    }

    public RecycleSearchAdapter(ArrayList<SearchRecyclerItem> itemArrayList){

    }

    @NonNull
    @Override
    public RecycleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        RecycleAdapterViewHolder recycleAdapterViewHolder = new RecycleAdapterViewHolder(view);
        return recycleAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
