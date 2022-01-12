package com.example.labcdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerBillboardSubAdapter extends RecyclerView.Adapter<RecyclerBillboardSubAdapter.RecyclerBillboardSubViewHolder> {

    private ArrayList<BillboardSubItem> itemArrayList;

    public static class RecyclerBillboardSubViewHolder extends RecyclerView.ViewHolder {

        private TextView lineTextView, destinationTextView, timeTextView;

        public RecyclerBillboardSubViewHolder(@NonNull View itemView) {
            super(itemView);
            lineTextView = itemView.findViewById(R.id.billboardSubItemLineText);
            destinationTextView = itemView.findViewById(R.id.billboardSubItemDestText);
            timeTextView = itemView.findViewById(R.id.billboardSubItemTimeText);
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

    public RecyclerBillboardSubAdapter(ArrayList<BillboardSubItem> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public RecyclerBillboardSubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerBillboardSubViewHolder recyclerBillboardSubViewHolder = new RecyclerBillboardSubViewHolder(view);
        return recyclerBillboardSubViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBillboardSubViewHolder holder, int position) {
        BillboardSubItem currentItem = itemArrayList.get(position);
        holder.getLineTextView().setText(currentItem.getLine());
        holder.getDestinationTextView().setText(currentItem.getDestination());
        holder.getTimeTextView().setText(currentItem.getDisplayTime());


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.billboard_subitem;
    }
}
