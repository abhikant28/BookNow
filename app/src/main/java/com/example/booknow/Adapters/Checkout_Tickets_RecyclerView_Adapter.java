package com.example.booknow.Adapters;

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
import com.example.booknow.Database.SharedPrefs;
import com.example.booknow.FinalCheckout;
import com.example.booknow.R;


public class Checkout_Tickets_RecyclerView_Adapter extends ListAdapter<Ticket, Checkout_Tickets_RecyclerView_Adapter.MyViewHolder> {
    private static final DiffUtil.ItemCallback<Ticket> DIFF_CALLBACK_TICKETS = new DiffUtil.ItemCallback<Ticket>() {
        @Override
        public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
            return oldItem.getQuant()==newItem.getQuant();
        }
    };

    public Checkout_Tickets_RecyclerView_Adapter() {
        super(DIFF_CALLBACK_TICKETS);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_bookings_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Checkout_Tickets_RecyclerView_Adapter.MyViewHolder holder, int position) {
        String title = getItem(position).getEventName();
        String quant = String.valueOf(getItem(position).getQuant());
        String amount = getItem(position).getTotalAmount();
        String date = getItem(position).getDate().split("_")[0]+getItem(position).getDate().split("_")[1];

        Log.i("POSITION", title);
        holder.tv_title.setText(title);
        holder.tv_amount.setText("₹"+amount);
        holder.tv_tickets.setText(date+", "+quant+" Tickets");
        holder.tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_amount;
        private TextView tv_tickets;
        private TextView tv_remove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tickets = itemView.findViewById(R.id.checkout_ticket_listItem_tv_tickets);
            tv_title = itemView.findViewById(R.id.checkout_ticket_listItem_tv_eventName);
            tv_amount = itemView.findViewById(R.id.checkout_ticket_listItem_tv_amount);
            tv_remove = itemView.findViewById(R.id.checkout_ticket_listItem_tv_remove);

            tv_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FinalCheckout.removeTicket(getAdapterPosition(), view.getContext());

//                    SharedPrefs.updatePending();
                }
            });

        }
    }

//    private void clicked(String id, Context context) {
//
//        Intent intent = new Intent(context, TicketPage.class);
//        intent.putExtra("ID", id);
//        context.startActivity(intent);
//    }


}
