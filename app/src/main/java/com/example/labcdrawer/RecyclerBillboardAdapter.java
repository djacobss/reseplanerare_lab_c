package com.example.labcdrawer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerBillboardAdapter extends RecyclerView.Adapter<RecyclerBillboardAdapter.RecyclerBillboardViewHolder> {

    private ArrayList<BillboardItem> itemArrayList;
    private Context context;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public static class RecyclerBillboardViewHolder extends RecyclerView.ViewHolder {

        private TextView stationNameTextView, nextLineTextView, nextTimeTextView;
        private CardView cardView;
        private ImageView arrowView;
        private RecyclerView subRecyclerView;
        private ConstraintLayout expandableLayout;

        public RecyclerBillboardViewHolder(@NonNull View itemView) {
            super(itemView);
            stationNameTextView = itemView.findViewById(R.id.realTimeBillboardItemStationText);
            nextLineTextView = itemView.findViewById(R.id.realTimeBillboardItemLineText);
            nextTimeTextView = itemView.findViewById(R.id.realTimeBillboardItemTimeText);
            cardView = itemView.findViewById(R.id.billboardCardItem);
            arrowView = itemView.findViewById(R.id.billboardArrowView);
            subRecyclerView = itemView.findViewById(R.id.billboardSubItemList);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
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

        public CardView getCardView() {
            return cardView;
        }

        public ImageView getArrowView() {
            return arrowView;
        }

        public RecyclerView getSubRecyclerView() {
            return subRecyclerView;
        }

        public ConstraintLayout getExpandableLayout() {
            return expandableLayout;
        }
    }

    public RecyclerBillboardAdapter(ArrayList<BillboardItem> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerBillboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerBillboardViewHolder recyclerBillboardViewHolder = new RecyclerBillboardViewHolder(view);
        return recyclerBillboardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBillboardViewHolder holder, int position) {
        BillboardItem currentItem = itemArrayList.get(position);
        holder.getStationNameTextView().setText(currentItem.getStationName());
        holder.getNextLineTextView().setText(currentItem.getNextLineNumber());
        holder.getNextTimeTextView().setText(currentItem.getNextDisplayTime());

        boolean isExpanded = currentItem.isOpen();
        holder.getExpandableLayout().setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.getArrowView().setImageResource(isExpanded ? R.drawable.ic_baseline_keyboard_arrow_down_24 : R.drawable.ic_baseline_keyboard_arrow_up_24);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.getSubRecyclerView().getContext(),
                LinearLayoutManager.VERTICAL, false);
        layoutManager.setInitialPrefetchItemCount(currentItem.getBillboardSubItems().size());

        RecyclerBillboardSubAdapter subAdapter = new RecyclerBillboardSubAdapter(currentItem.getBillboardSubItems());

        holder.getSubRecyclerView().setLayoutManager(layoutManager);
        holder.getSubRecyclerView().setAdapter(subAdapter);

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getArrowView().setImageResource(isExpanded ? R.drawable.ic_baseline_keyboard_arrow_down_24 : R.drawable.ic_baseline_keyboard_arrow_up_24);
                currentItem.setOpen(!currentItem.isOpen());
                notifyItemChanged(holder.getAbsoluteAdapterPosition());
            }
        });
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
