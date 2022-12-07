package com.example.booknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.booknow.AppObjects.Ticket;
import com.example.booknow.Database.SharedPrefs;

import java.util.ArrayList;
import java.util.Arrays;

public class TicketView extends AppCompatActivity {

    TextView tv_name;
    TextView tv_type;
    TextView tv_amount;
    TextView tv_timing;
    TextView tv_seats;
    TextView tv_status;
    TextView tv_date;
    Button b_cancel;

    int pos;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),BookedTickets.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_view);

        pos = getIntent().getExtras().getInt("POS");
        ArrayList<Ticket> ticketsList = new ArrayList<>();
        ticketsList = SharedPrefs.loadTickets(getApplicationContext());
        Ticket ticket = ticketsList.get(pos);

        tv_name = findViewById(R.id.tickectView_tv_name);
        tv_type = findViewById(R.id.tickectView_tv_type);
        tv_amount = findViewById(R.id.tickectView_tv_amountPaid);
        tv_timing = findViewById(R.id.tickectView_tv_time);
        tv_seats = findViewById(R.id.tickectView_tv_seats);
        tv_status = findViewById(R.id.tickectView_tv_status);
        tv_date=findViewById(R.id.tickectView_tv_date);
        b_cancel = findViewById(R.id.tickectView_btn_cancel);

        tv_date.setText("Date: "+ticket.getDate().split("_")[0]+" "+ticket.getDate().split("_")[1]);
        tv_name.setText(ticket.getEventName());
        tv_type.setText(ticket.getEventType());
        tv_amount.setText("Total Amount Paid: ₹"+ticket.getTotalAmount());
        tv_timing.setText("Timings: "+ticket.getTimings());
        tv_seats.setText("Seats: "+Arrays.deepToString(ticket.getSeatNums()));
        tv_status.setText(ticket.getStatus());
        ArrayList<Ticket> finalTicketsList = ticketsList;
        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.getMenu().add(Menu.NONE, 0, 0, "Confirm");
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        ticket.setStatus("CANCELLED");
                        finalTicketsList.remove(pos);
                        finalTicketsList.add(pos,ticket);
                        SharedPrefs.updateBookedTickets(view.getContext(), finalTicketsList);
                        tv_status.setText("CANCELLED");
                        tv_status.setTextColor(Color.parseColor("#BD0808"));
                        b_cancel.setVisibility(View.INVISIBLE);
                        return false;
                    }
                });
            }
        });
        if(ticket.getStatus().equals("CANCELLED")){
            b_cancel.setVisibility(View.INVISIBLE);
            tv_status.setTextColor(Color.parseColor("#BD0808"));
        }
    }
}