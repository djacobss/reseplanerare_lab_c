package com.example.labcdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerTripsAdapter extends RecyclerView.Adapter<RecyclerTripsAdapter.RecyclerTripsViewHolder> {

    private ArrayList<TripItem> itemArrayList;
    private Context context;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public static class RecyclerTripsViewHolder extends RecyclerView.ViewHolder{

        private CardView mainCard;
        private TextView startTimeText, endTimeText, startStationText, endStationText;
        private RecyclerView lineRecyclerView, expandableRecyclerView;
        private ImageView arrowImageView;
        private ConstraintLayout expandableLayout;

        public RecyclerTripsViewHolder(@NonNull View itemView) {
            super(itemView);
            mainCard = itemView.findViewById(R.id.tripsSearchCard);
            startTimeText = itemView.findViewById(R.id.tripsSearchItemStartTime);
            startStationText = itemView.findViewById(R.id.tripsSearchItemStartStation);
            endTimeText = itemView.findViewById(R.id.tripsSearchItemEndTime);
            endStationText = itemView.findViewById(R.id.tripsSearchItemEndStation);
            lineRecyclerView = itemView.findViewById(R.id.tripsSearchItemTopRecycler);
            expandableRecyclerView = itemView.findViewById(R.id.expandableRecyclerViewTrips);
            arrowImageView = itemView.findViewById(R.id.tripsSearchArrowImageView);
            expandableLayout = itemView.findViewById(R.id.expandableTripsItemLayout);
        }

        public CardView getMainCard() {
            return mainCard;
        }

        public TextView getStartTimeText() {
            return startTimeText;
        }

        public TextView getEndTimeText() {
            return endTimeText;
        }

        public TextView getStartStationText() {
            return startStationText;
        }

        public TextView getEndStationText() {
            return endStationText;
        }

        public RecyclerView getLineRecyclerView() {
            return lineRecyclerView;
        }

        public RecyclerView getExpandableRecyclerView() {
            return expandableRecyclerView;
        }

        public ImageView getArrowImageView() {
            return arrowImageView;
        }

        public ConstraintLayout getExpandableLayout() {
            return expandableLayout;
        }
    }

    public RecyclerTripsAdapter(ArrayList<TripItem> itemArrayList, Context context){
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerTripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerTripsViewHolder recyclerTripsViewHolder = new RecyclerTripsViewHolder(view);
        return recyclerTripsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTripsViewHolder holder, int position) {
        TripItem currentItem = itemArrayList.get(position);
        currentItem.setupTripLineItems();
        currentItem.setUpSubItems();
        holder.getStartStationText().setText(currentItem.getStartLocationName());
        holder.getStartTimeText().setText(currentItem.getStartTime());
        holder.getEndStationText().setText(currentItem.getEndLocationName());
        holder.getEndTimeText().setText(currentItem.getEndTime());

        boolean isOpen = currentItem.isExpanded();
        holder.getExpandableLayout().setVisibility(isOpen ? View.VISIBLE : View.GONE);
        holder.getArrowImageView().setImageResource(isOpen ? R.drawable.ic_baseline_keyboard_arrow_down_24 : R.drawable.ic_baseline_keyboard_arrow_up_24);

        LinearLayoutManager layoutManagerLines = new LinearLayoutManager(
                holder.getLineRecyclerView().getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        layoutManagerLines.setInitialPrefetchItemCount(currentItem.getLineList().size());

        RecyclerTripLineAdapter lineAdapter = new RecyclerTripLineAdapter(currentItem.getTripLineItems(),context);

        holder.getLineRecyclerView().setLayoutManager(layoutManagerLines);
        holder.getLineRecyclerView().setAdapter(lineAdapter);

        LinearLayoutManager layoutManagerSub = new LinearLayoutManager(
                holder.getExpandableRecyclerView().getContext(),
                LinearLayoutManager.VERTICAL, false
        );
        layoutManagerSub.setInitialPrefetchItemCount(currentItem.getLineList().size());

        RecyclerTripSubAdapter subAdapter = new RecyclerTripSubAdapter(currentItem.getTripSubItems(),context);

        holder.getExpandableRecyclerView().setLayoutManager(layoutManagerSub);
        holder.getExpandableRecyclerView().setAdapter(subAdapter);

        holder.getMainCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getArrowImageView().setImageResource(isOpen ? R.drawable.ic_baseline_keyboard_arrow_down_24 : R.drawable.ic_baseline_keyboard_arrow_up_24);
                currentItem.setExpanded(!currentItem.isExpanded());
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
        return R.layout.trips_search_item;
    }

}
