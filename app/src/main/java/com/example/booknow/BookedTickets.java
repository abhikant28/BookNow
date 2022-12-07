package com.example.booknow;

import static com.example.booknow.EventsListing.SelectedDay;
import static com.example.booknow.EventsListing.event_recyclerView_adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.booknow.Adapters.BookedTickets_recyclerView_Adapter;
import com.example.booknow.AppObjects.Event;
import com.example.booknow.AppObjects.Ticket;
import com.example.booknow.Database.SharedPrefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BookedTickets extends AppCompatActivity {

    RecyclerView rv_tickets;
    BookedTickets_recyclerView_Adapter bookedTickets_recyclerView_adapter= new BookedTickets_recyclerView_Adapter();
    TextView tv_sortDate;
    TextView tv_movies;
    TextView tv_comedy;
    TextView tv_plays;


    CardView cv_all;
    CardView cv_movies;
    CardView cv_comedy;
    CardView cv_plays;

    static boolean allFilter=false;
    boolean movieFilter=false;
    boolean comedyFilter=false;
    boolean playsFilter=false;

    TextView tv_text;
    ArrayList<Ticket> tickets;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,LandingPage.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_tickets);
        setViews();

        tv_text=findViewById(R.id.bookedTickets_tv_text);
        rv_tickets=findViewById(R.id.bookedTickets_rv_tickets);

        tickets= SharedPrefs.loadTickets(getApplicationContext());
        if(tickets.size()==0)tv_text.setVisibility(View.VISIBLE);
        bookedTickets_recyclerView_adapter.submitList(tickets);
        rv_tickets.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_tickets.setHasFixedSize(true);
        rv_tickets.setItemAnimator(new DefaultItemAnimator());
        rv_tickets.setAdapter(bookedTickets_recyclerView_adapter);

    }


    private void setViews() {

        tv_movies=findViewById(R.id.bookedTickets_tv_filter_movies);
        tv_comedy=findViewById(R.id.bookedTickets_tv_filter_comedy);
        tv_plays=findViewById(R.id.bookedTickets_tv_filter_play);

        tv_sortDate=findViewById(R.id.bookedTickets_tv_sort);
        cv_movies=findViewById(R.id.bookedTickets_cv_filter_movies);
        cv_comedy=findViewById(R.id.bookedTickets_cv_filter_comedy);
        cv_plays=findViewById(R.id.bookedTickets_cv_filter_play);

//        tv_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(allFilter){
//                    allFilter=false;
//                }else{
//                    allFilter=true;
//                }
//                invertCol(cv_all,tv_all,allFilter);
//                setList(SelectedDay);
//            }
//        });
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
                Log.i("INVERT:::::", "CALLED");

                if(playsFilter){
                    playsFilter=false;
                }else{
                    playsFilter=true;
                }
                invertCol(cv_plays,tv_plays,playsFilter);
                filterList();
            }
        });
        tv_sortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort();
            }
        });

    }

    private ArrayList<Ticket> filterList() {
        if(!playsFilter && !movieFilter && !comedyFilter){
            setList();
            return null;
        }
        if(playsFilter && movieFilter && comedyFilter){
            setList();
            return null;
        }
        ArrayList<Ticket> newList=new ArrayList();
        for(Ticket e:tickets){
            if(movieFilter && e.getEventType().equals("Movie")){
                newList.add(e);
            }
            if(playsFilter && e.getEventType().equals("Play")){
                newList.add(e);
            }
            if(comedyFilter && e.getEventType().equals("Comedy")){
                newList.add(e);
            }
        }
        bookedTickets_recyclerView_adapter.submitList(null);
        bookedTickets_recyclerView_adapter.submitList(newList);
        return newList;
    }

    private void setList() {
        event_recyclerView_adapter.submitList(null);
        bookedTickets_recyclerView_adapter.submitList(tickets);
    }

    private void invertCol(CardView view, TextView tv, boolean b) {

        Log.i("INVERT:::::", "CALLED");
        if(!b){
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            tv.setTextColor(Color.parseColor("#888888"));
        }else{
            view.setBackgroundColor(Color.parseColor("#BD0808"));
            tv.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
    }
    public void sort(){
        ArrayList<Ticket> newList=new ArrayList<Ticket>(tickets);
        Collections.sort(newList, new Comparator<Ticket>(){
            public int compare(Ticket obj1, Ticket obj2) {
                // ## Ascending order
                return obj1.getDate().split("_")[0].compareToIgnoreCase(obj2.getDate().split("_")[0]); // To compare string values
                // return Integer.valueOf(obj1.empId).compareTo(Integer.valueOf(obj2.empId)); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
            }
        });
        bookedTickets_recyclerView_adapter.submitList(null);
        bookedTickets_recyclerView_adapter.submitList(newList);
    }

}