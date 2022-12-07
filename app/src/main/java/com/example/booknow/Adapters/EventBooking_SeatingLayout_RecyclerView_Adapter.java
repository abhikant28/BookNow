package com.example.booknow.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booknow.AppObjects.Seat;
import com.example.booknow.R;

import java.util.HashSet;
import java.util.List;


public class EventBooking_SeatingLayout_RecyclerView_Adapter extends ListAdapter<Seat, EventBooking_SeatingLayout_RecyclerView_Adapter.MyViewHolder> {
    public static HashSet<Object> reserving= new HashSet<>();
    int prevCol;
    TextView tv_count;


    private  static final DiffUtil.ItemCallback<Seat> DIFF_CALLBACK_NOTES = new DiffUtil.ItemCallback<Seat>() {
        @Override
        public boolean areItemsTheSame(@NonNull Seat oldItem, @NonNull Seat newItem) {
            return oldItem.getSeatId()==newItem.getSeatId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Seat oldItem, @NonNull Seat newItem) {
            return oldItem.isReserved() == newItem.isReserved();
        }
    };

    public EventBooking_SeatingLayout_RecyclerView_Adapter() {
        super(DIFF_CALLBACK_NOTES);
    }

    public EventBooking_SeatingLayout_RecyclerView_Adapter(TextView tv_count) {
        super(DIFF_CALLBACK_NOTES);
        this.tv_count=tv_count;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventbooking_seat_layout_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventBooking_SeatingLayout_RecyclerView_Adapter.MyViewHolder holder, int position) {


        holder.tv_seatNum.setText(String.valueOf((position+1)%15!=0?((position+1)%15):15));
        if(getItem(position).isReserved()){
            Log.i("SEAT:::::::;","Reserved");
            holder.cv_seatBackground.setBackgroundColor(Color.parseColor("#61655F"));
            holder.tv_seatNum.setTextColor(Color.parseColor("#FF000000"));
        }
    }

      public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_seatBackground;
        private TextView tv_seatNum;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prevCol=itemView.getDrawingCacheBackgroundColor();
            tv_seatNum = itemView.findViewById(R.id.EventLayout_seat_listItem_textView_seatNumber);
            cv_seatBackground = itemView.findViewById(R.id.EventLayout_seat_listItem_cardView_event);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!getItem(getAdapterPosition()).isReserved())clicked(getItem(getAdapterPosition()),cv_seatBackground,tv_seatNum);
                }
            });

        }
    }
    private void clicked(Seat seat, CardView cv, TextView tv) {
        Log.i("CLICKED:::::::", String.valueOf(reserving.size()));
        if(!reserving.contains(seat.getSeatId())){

            reserving.add(seat.getSeatId());

            prevCol=cv.getCardBackgroundColor().getDefaultColor();
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            cv.setBackgroundColor(Color.parseColor("#46C80F"));
        }else{
            reserving.remove(seat.getSeatId());
            cv.setBackgroundColor(prevCol);
            tv.setTextColor(Color.parseColor("#46C80F"));
        }
        tv_count.setText(String.valueOf(reserving.size())+" Seat(s)");
    }


}
