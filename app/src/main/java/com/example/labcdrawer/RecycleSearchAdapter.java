package com.example.labcdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleSearchAdapter extends RecyclerView.Adapter<RecycleSearchAdapter.RecycleAdapterViewHolder> {

    private ArrayList<SearchRecyclerItem> itemArrayList;

    public static class RecycleAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView searchResultTextView;

        public RecycleAdapterViewHolder(View itemView){
            super(itemView);
            searchResultTextView = itemView.findViewById(R.id.realTimeSearchCardViewText);
        }

        public TextView getSearchResultTextView(){
            return searchResultTextView;
        }

    }

    public RecycleSearchAdapter(ArrayList<SearchRecyclerItem> itemArrayList){
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public RecycleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        RecycleAdapterViewHolder recycleAdapterViewHolder = new RecycleAdapterViewHolder(view);
        return recycleAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterViewHolder holder, int position) {
        SearchRecyclerItem currentItem = itemArrayList.get(position);
        holder.getSearchResultTextView().setText(currentItem.getPlaceName());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.example_item;
    }
}