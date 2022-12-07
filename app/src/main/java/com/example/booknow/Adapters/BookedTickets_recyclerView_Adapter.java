package com.example.booknow.Adapters;

import android.content.Intent;
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

import com.example.booknow.AppObjects.Ticket;
import com.example.booknow.BookedTickets;
import com.example.booknow.FinalCheckout;
import com.example.booknow.R;
import com.example.booknow.TicketView;

import java.util.Arrays;


public class BookedTickets_recyclerView_Adapter extends ListAdapter<Ticket, BookedTickets_recyclerView_Adapter.MyViewHolder> {
    private static final DiffUtil.ItemCallback<Ticket> DIFF_CALLBACK_TICKETS = new DiffUtil.ItemCallback<Ticket>() {
        @Override
        public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
            return oldItem.getQuant()==newItem.getQuant();
        }
    };

    public BookedTickets_recyclerView_Adapter() {
        super(DIFF_CALLBACK_TICKETS);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_tickets_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedTickets_recyclerView_Adapter.MyViewHolder holder, int position) {
        String title = getItem(position).getEventName();
        String quant = String.valueOf(getItem(position).getQuant());
        String s=Arrays.deepToString(getItem(position).getSeatNums());
        String seats = s.substring(1,s.length()-1);
        String status = getItem(position).getStatus();
        String date=getItem(position).getDate().split("_")[0]+" "+getItem(position).getDate().split("_")[1];
        String time= getItem(position).getTimings();

        Ticket t=getItem(position);
        holder.tv_time.setText(time);
        holder.tv_date.setText(date);
        holder.tv_title.setText(title);
        holder.tv_count.setText(quant);
        holder.tv_seats.setText(seats);
        holder.tv_status.setText(status);
        if(status.equals("CANCELLED")){
            holder.tv_status.setTextColor(Color.parseColor("#BD0808"));
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_count;
        private TextView tv_time;
        private TextView tv_seats;
        private TextView tv_status;
        private TextView tv_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.bookedTickets_ticket_listItem_tv_time);
            tv_title = itemView.findViewById(R.id.bookedTickets_ticket_listItem_tv_eventName);
            tv_count = itemView.findViewById(R.id.bookedTickets_ticket_listItem_tv_count);
            tv_seats = itemView.findViewById(R.id.bookedTickets_ticket_listItem_tv_seats);
            tv_status=itemView.findViewById(R.id.bookedTickets_ticket_listItem_tv_status);
            tv_date=itemView.findViewById(R.id.bookedTickets_ticket_listItem_tv_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(), TicketView.class);
                    intent.putExtra("POS", getAdapterPosition());
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
