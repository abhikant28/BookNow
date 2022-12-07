package com.example.booknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.booknow.Adapters.EventBooking_SeatingLayout_RecyclerView_Adapter;
import com.example.booknow.AppObjects.Event;
import com.example.booknow.AppObjects.Seat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EventBooking extends AppCompatActivity {

    RecyclerView rv_seatingLayout;
    EventBooking_SeatingLayout_RecyclerView_Adapter eventBooking_seatingLayout_rv_adapter= new EventBooking_SeatingLayout_RecyclerView_Adapter();

    TextView tv_time;
    TextView tv_count;
    Toolbar tb_title;
    Button b_book;
    List<Seat> seatsList= new ArrayList<>();

    Event event;
    int slot;
    HashSet<String> reservedSeats=new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_booking);

        tv_count=findViewById(R.id.EventBooking_toolbar_ticketCount);
        tv_time=findViewById(R.id.EventBooking_tv_timing);
        tb_title=findViewById(R.id.EventBooking_toolbar_event);
        b_book=findViewById(R.id.EventBooking_btn_book);
        rv_seatingLayout= findViewById(R.id.EventBooking_recyclerView_seatingLayOut);

        String eId= getIntent().getExtras().getString("ID");
        slot= getIntent().getExtras().getInt("SLOT");
        event=LandingPage.eventsMap.get(eId);


        eventBooking_seatingLayout_rv_adapter=new EventBooking_SeatingLayout_RecyclerView_Adapter(tv_count);
        tb_title.setTitle(event.getName());
        UsefulFunctions.timeSlotHash();
//        Log.i("SLOT:::::", String.valueOf(slot)+"_"+UsefulFunctions.timingsToSlot.toString());
        tv_time.setText("Timing: "+String.valueOf(UsefulFunctions.timings[slot]));


        reservedSeats.addAll(Arrays.asList(LandingPage.SEAT_RESERVATIONS[EventsListing.SelectedDay][slot].split(",")));

        makeSeats(seatsList);

        eventBooking_seatingLayout_rv_adapter.submitList(seatsList);

        rv_seatingLayout.setLayoutManager( new GridLayoutManager(this,15));
        rv_seatingLayout.setHasFixedSize(true);
        rv_seatingLayout.setItemAnimator(new DefaultItemAnimator());
        rv_seatingLayout.setAdapter(eventBooking_seatingLayout_rv_adapter);

        b_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eventBooking_seatingLayout_rv_adapter.reserving.size()!=0){
                    Intent intent = new Intent(getApplicationContext(), BookingSummary.class);
                    intent.putExtra("ID", event.getId());
                    intent.putExtra("SLOT", slot);
                    String s = EventBooking_SeatingLayout_RecyclerView_Adapter.reserving.toString();
                    intent.putExtra("SEATS", s.substring(1, s.length() - 1));
                    startActivity(intent);
                }
            }
        });
    }

    private void makeSeats(List<Seat> list) {

        for(int i=1;i<151;i++){
            Seat seat= new Seat(String.valueOf(i),false,String.valueOf(i),String.valueOf((i/15)+1),String.valueOf(i%15));
            if(reservedSeats.contains(String.valueOf(i))){seat.setReserved(true);}
            list.add(seat);
        }
    }
}