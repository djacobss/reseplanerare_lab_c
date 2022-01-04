package com.example.labcdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerBillboardAdapter extends RecyclerView.Adapter<RecyclerBillboardAdapter.RecyclerBillboardViewHolder> {

    private ArrayList<BillboardItem> itemArrayList;
    private Context context;

    public static class RecyclerBillboardViewHolder extends RecyclerView.ViewHolder {

        private TextView stationNameTextView, nextLineTextView, nextTimeTextView, slideLineTextView, slideDestinationTextView, slideTimeTextView;
        private CardView cardItemView;

        public RecyclerBillboardViewHolder(@NonNull View itemView) {
            super(itemView);
            stationNameTextView = itemView.findViewById(R.id.realTimeBillboardItemStationText);
            nextLineTextView = itemView.findViewById(R.id.realTimeBillboardItemLineText);
            nextTimeTextView = itemView.findViewById(R.id.realTimeBillboardItemTimeText);
            slideLineTextView = itemView.findViewById(R.id.billboardItemAnimationLine);
            slideDestinationTextView = itemView.findViewById(R.id.billboardItemAnimationName);
            slideTimeTextView = itemView.findViewById(R.id.billboardItemAnimationTime);
            cardItemView = itemView.findViewById(R.id.billboardCardItem);
        }

        public TextView getStationNameTextView() {
            return stationNameTextView;
        }

        public TextView getNextLineTextView() {
            return nextLineTextView;
        }

        public TextView getNextTimeTextView() {
            return nextTimeTextView;
        }

        public TextView getSlideLineTextView() {
            return slideLineTextView;
        }

        public TextView getSlideDestinationTextView() {
            return slideDestinationTextView;
        }

        public TextView getSlideTimeTextView() {
            return slideTimeTextView;
        }

        public CardView getCardItemView() {
            return cardItemView;
        }
    }

    public RecyclerBillboardAdapter(ArrayList<BillboardItem> itemArrayList, Context context){
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerBillboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        RecyclerBillboardViewHolder recyclerBillboardViewHolder = new RecyclerBillboardViewHolder(view);
        return recyclerBillboardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBillboardViewHolder holder, int position) {
        BillboardItem currentItem = itemArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.billboard_item;
    }
}
