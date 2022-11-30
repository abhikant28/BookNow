package com.example.booknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class EventsListing extends AppCompatActivity {

    RecyclerView rv_eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventslist);

        rv_eventsList=findViewById(R.id.LandingPage_eventsFragment_recyclerView);


    }
}