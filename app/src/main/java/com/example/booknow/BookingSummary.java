package com.example.booknow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.booknow.AppObjects.Event;
import com.example.booknow.AppObjects.Ticket;
import com.example.booknow.Database.SharedPrefs;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;

public class BookingSummary extends AppCompatActivity {

    private Button b_checkout;
    private Button b_otherBooking;
    private Toolbar tb_title;
    private TextView tv_eventName;
    private TextView tv_eventTime;
    private TextView tv_seats;
    private TextView tv_eventDate;
    private TextView tv_ticketPrice;
    private TextView tv_totalAmount;
    private TextView tv_lang;
    private TextView tv_convFee;


    private String time;
    private String[] seats;
    private int convFee = 40;
    private Event event;
    private int slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);

        Intent intent = getIntent();
        event = LandingPage.eventsMap.get(intent.getExtras().getString("ID"));

        slot = intent.getExtras().getInt("SLOT");
        time = UsefulFunctions.timings[slot];
        seats = intent.getExtras().getString("SEATS").split(",");

        setViews();
        setClicks();
        setValues(event);
    }

    private void setValues(Event event) {
        tv_seats.setText(Arrays.deepToString(seats));
        tv_ticketPrice.setText("₹"+event.getPrice()+".00");
        tv_lang.setText(event.getLanguage());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, EventsListing.SelectedDay);
        tv_eventTime.setText(time);
        tv_eventDate.setText(c.get(Calendar.DATE)+" "+new DateFormatSymbols().getMonths()[(c.get(Calendar.MONTH))].substring(0,3)+", "+c.get(Calendar.YEAR));
        tv_convFee.setText(("₹"+convFee + ".00"));
        tv_eventName.setText(event.getName());
        int ta= Integer.parseInt(String.valueOf(Integer.parseInt(event.getPrice())*seats.length + convFee));
        tv_totalAmount.setText("₹"+String.valueOf(ta)+".00");
    }

    private void setClicks() {
        b_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePendingBookings(event.getId()+seats[0],EventsListing.SelectedDay,slot,seats,event.getId());
                Log.i("BUTTON CLICK::::::","CHECKOUT");
                startActivity(new Intent(getApplicationContext(), FinalCheckout.class));
                finish();
            }
        });
        b_otherBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePendingBookings(event.getId()+seats[0],EventsListing.SelectedDay,slot,seats,event.getId());
                Log.i("BUTTON CLICK::::::","HOME");
                startActivity(new Intent(getApplicationContext(), LandingPage.class));
                finish();
            }
        });
    }

    private void savePendingBookings(String id, int selectedDay, int slot, String[] seats, String eventID) {
        Ticket ticket= new Ticket(event.getType(),String.valueOf(Integer.parseInt(event.getPrice())*seats.length),event.getName(),id,String.valueOf(selectedDay),seats,String.valueOf(slot),eventID);
        SharedPrefs.saveTickets(getApplicationContext(),ticket);
    }


    private void setViews() {
        b_checkout = findViewById(R.id.BookingSummary_btn_checkOut);
        b_otherBooking = findViewById(R.id.BookingSummary_btn_moreBooking);
        tb_title = findViewById(R.id.BookingSummary_tb_title);
        tv_convFee = findViewById(R.id.BookingSummary_tv_convFee);
        tv_eventName = findViewById(R.id.BookingSummary_tv_eventName);
        tv_lang = findViewById(R.id.BookingSummary_tv_lang);
        tv_eventTime = findViewById(R.id.BookingSummary_tv_dateTime);
        tv_ticketPrice = findViewById(R.id.BookingSummary_tv_price);
        tv_totalAmount = findViewById(R.id.BookingSummary_tv_totalAmount);
        tv_seats = findViewById(R.id.BookingSummary_tv_seats);
        tv_eventDate=findViewById(R.id.BookingSummary_tv_date);
    }
}