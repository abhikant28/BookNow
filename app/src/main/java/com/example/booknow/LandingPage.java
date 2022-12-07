package com.example.booknow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.booknow.AppObjects.Event;
import com.example.booknow.Database.SharedPrefs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.List;

public class LandingPage extends AppCompatActivity {

    public static HashMap<String,Event> eventsMap=new HashMap<>();
    public static String[][] EVENTS_SCHEDULE= new String[7][8];
    public static String[][] SEAT_RESERVATIONS= new String[7][8];


    Toolbar tb_top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setClicks();
        setViews();

        eventsMap=new HashMap<>();
        BottomNavigationView bottomNav = findViewById(R.id.LandingPage_bottomNavigationBar);
        bottomNav.setOnNavigationItemSelectedListener(navItemSelectedListener);

        UsefulFunctions.timeSlotHash();
        UsefulFunctions.GetEvents();
        UsefulFunctions.GetSchedule();

        Log.i("Reserve::::::::::", LandingPage.eventsMap.toString());


        tb_top.setTitle("Hi, "+SharedPrefs.loadUserData(getApplicationContext()).split("&&")[0]);
//        UsefulFunctions.setReserved();

        getSupportFragmentManager().beginTransaction().replace(R.id.LandingPage_frameLayout, new LandingPage_Fragment_Home()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;
            switch (item.getItemId()){
                case R.id.navigation_home:
                    selectedFragment=new LandingPage_Fragment_Home();
                    break;
                case R.id.navigation_bookings:
                    selectedFragment=new LandingPage_Fragment_Bookings();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.LandingPage_frameLayout, selectedFragment).commit();
            return true;
        }
    };
    private void setViews() {
        tb_top=findViewById(R.id.LandingPage_toolbar);
    }

    private void setClicks() {
    }
}