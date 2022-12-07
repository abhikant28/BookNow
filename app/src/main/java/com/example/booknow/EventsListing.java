package com.example.booknow;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booknow.Adapters.Dates_RecyclerView_Adapter;
import com.example.booknow.Adapters.Event_RecyclerView_Adapter;
import com.example.booknow.AppObjects.Event;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class EventsListing extends AppCompatActivity {

    static int SelectedDay=0;

    RecyclerView rv_eventsList;
    RecyclerView rv_dates;

    static HashSet<Event> eventsList = new HashSet<>();
    List<String> dateList = new ArrayList<>();

    TextView tv_all;
    TextView tv_movies;
    TextView tv_comedy;
    TextView tv_plays;

    CardView cv_all;
    CardView cv_movies;
    CardView cv_comedy;
    CardView cv_plays;

    Toolbar tb_title;

    static boolean allFilter=false;
    static boolean movieFilter=false;
    static boolean comedyFilter=false;
    static boolean playsFilter=false;

    public static Event_RecyclerView_Adapter event_recyclerView_adapter = new Event_RecyclerView_Adapter();
    Dates_RecyclerView_Adapter dates_recyclerView_adapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,LandingPage.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventslist);

        setViews();
        makeDates();
        setList(0);
        setAdapters();

        setSupportActionBar(tb_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(getIntent().getExtras().getString("TYPE")!=null) {
            String s=getIntent().getExtras().getString("TYPE");
            switch (s){
                case "1":
                    movieFilter=true;
                    invertCol(cv_movies, tv_movies, movieFilter);
                    break;
                case "2":
                    playsFilter=true;
                    invertCol(cv_plays, tv_plays, playsFilter);
                    break;
                case "3":
                    comedyFilter=true;
                    invertCol(cv_comedy, tv_comedy, comedyFilter);
                    break;
            }
            filterList();
        }
    }

    private void setViews() {

        tv_all=findViewById(R.id.EventsListing_tv_filter_all);
        tv_movies=findViewById(R.id.EventsListing_tv_filter_movies);
        tv_comedy=findViewById(R.id.EventsListing_tv_filter_comedy);
        tv_plays=findViewById(R.id.EventsListing_tv_filter_play);

        cv_all=findViewById(R.id.EventsListing_cv_filter_all);
        cv_movies=findViewById(R.id.EventsListing_cv_filter_movies);
        cv_comedy=findViewById(R.id.EventsListing_cv_filter_comedy);
        cv_plays=findViewById(R.id.EventsListing_cv_filter_play);
        tb_title=findViewById(R.id.EventsListing_toolbar_title);

        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allFilter){
                    allFilter=false;
                }else{
                    allFilter=true;
                }
                invertCol(cv_all,tv_all,allFilter);
                setList(SelectedDay);
            }
        });
        tv_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movieFilter){
                    movieFilter=false;
                }else{
                    movieFilter=true;
                }
                invertCol(cv_movies,tv_movies,movieFilter);
                filterList();
            }
        });
        tv_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(comedyFilter){
                    comedyFilter=false;
                }else{
                    comedyFilter=true;
                }
                invertCol(cv_comedy,tv_comedy,comedyFilter);
                filterList();
            }
        });
        tv_plays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playsFilter){
                    playsFilter=false;
                }else{
                    playsFilter=true;
                }
                invertCol(cv_plays,tv_plays,playsFilter);
                filterList();
            }
        });
        rv_eventsList = findViewById(R.id.EventsListing_recyclerView_events);
        rv_dates = findViewById(R.id.EventsListing_recyclerView_eventDates);

    }

    private static ArrayList<Event> filterList() {

        ArrayList<Event> newList=new ArrayList();
        if(eventsList.size()!=0){
            for (Event e : eventsList) {
                if(e!=null){
                    if (movieFilter && e.getType().equals("Movie")) {
                        newList.add(e);
                    }
                    if (playsFilter && e.getType().equals("Play")) {
                        newList.add(e);
                    }
                    if (comedyFilter && e.getType().equals("Comedy")) {
                        newList.add(e);
                    }
                }else{
                    Log.i("NULLLLL", "NULLLLLLLLL");
                }
            }
        }
        event_recyclerView_adapter.submitList(null);
        event_recyclerView_adapter.submitList(newList);
        return newList;
    }

    private void invertCol(CardView view, TextView tv,boolean b) {

        if(!b){
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            tv.setTextColor(Color.parseColor("#888888"));
        }else{
            view.setBackgroundColor(Color.parseColor("#BD0808"));
            tv.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
    }

    public static void setList(int i) {
        eventsList.clear();
        for(String s: LandingPage.EVENTS_SCHEDULE[i]){
            eventsList.add(LandingPage.eventsMap.get(s));
        }
        if(allFilter){
            event_recyclerView_adapter.submitList(null);
            event_recyclerView_adapter.submitList(new ArrayList<>(eventsList));

        }else
        {
            filterList();
        }
        SelectedDay=i;
    }

    private void makeDates() {
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            dateList.add(new DateFormatSymbols().getWeekdays()[(c.get(Calendar.DAY_OF_WEEK)-1)]+"_"+c.get(Calendar.DATE)+"_"+new DateFormatSymbols().getMonths()[(c.get(Calendar.MONTH))].substring(0,3));
            c.add(Calendar.DATE, 1);
        }
//        Log.i("DATE LIST:::::::::::",dateList.toString());
    }

    private void setAdapters() {
        dates_recyclerView_adapter = new Dates_RecyclerView_Adapter();
        dates_recyclerView_adapter.submitList(dateList);
        event_recyclerView_adapter.submitList(new ArrayList<>(eventsList));
        rv_dates.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_dates.setHasFixedSize(true);
        rv_dates.setItemAnimator(new DefaultItemAnimator());
        rv_dates.setAdapter(dates_recyclerView_adapter);
        /////
        rv_eventsList.setLayoutManager(new GridLayoutManager(this, 2));
        rv_eventsList.setHasFixedSize(true);
        rv_eventsList.setItemAnimator(new DefaultItemAnimator());
        rv_eventsList.setAdapter(event_recyclerView_adapter);
        rv_dates.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                rv_dates.findViewHolderForAdapterPosition(0).itemView.performClick();
                rv_dates.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }
}