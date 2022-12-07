package com.example.booknow.Adapters;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booknow.AppObjects.Event;
import com.example.booknow.EventsListing;
import com.example.booknow.LandingPage;
import com.example.booknow.R;

import java.util.List;


public class Dates_RecyclerView_Adapter extends ListAdapter<String, Dates_RecyclerView_Adapter.MyViewHolder> {
    View prevView;
    int prevCol;
    private static final DiffUtil.ItemCallback<String> DIFF_CALLBACK_NOTES = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return false;
        }
    };

    public Dates_RecyclerView_Adapter() {
        super(DIFF_CALLBACK_NOTES);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_listing_dates_list_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Dates_RecyclerView_Adapter.MyViewHolder holder, int position) {
        String date = getItem(position).split("_")[1];
        String month =  getItem(position).split("_")[2];
        String days=getItem(position).split("_")[0];

        holder.tv_date.setText(date);
        holder.tv_day.setText(days);
        holder.tv_month.setText(month);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_day;
        private TextView tv_date;
        private TextView tv_month;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_day = itemView.findViewById(R.id.eventListings_datesListItem_day);
            tv_date = itemView.findViewById(R.id.eventListings_datesListItem_date);
            tv_month = itemView.findViewById(R.id.eventListings_datesListItem_month);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CLICKkkk", "adsdawd");
                    if(prevView!=null) {
                        prevView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    }
                    prevView=itemView;
                    prevCol=itemView.getDrawingCacheBackgroundColor();
                    itemView.setBackgroundColor(Color.parseColor("#FFD14646"));
                    EventsListing.setList(getAdapterPosition());
                }
            });
        }
    }
}
