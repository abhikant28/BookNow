package com.example.booknow.Database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.booknow.AppObjects.Ticket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPrefs {
    private static String SHARED_PREFS="BOOKNOW_PREFS";

    public static void firstTime(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE );
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean("first", false);
        editor.apply();
    }
    public static boolean checkFirst(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE );
        return sharedPreferences.getBoolean("first", true);
    }

    public static void saveUserData(Context context, String name, String num){
        SharedPreferences sharedPreferences= context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE );
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("userData", name+"&&"+num);
        editor.apply();
        firstTime(context);
    }
    public static String loadUserData(Context cxt){
        SharedPreferences sharedPreferences= cxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userData", "Abhikant_9876543210");
    }

    public static void saveTickets(Context cxt, Ticket ticket){
        ArrayList<Ticket> prevTickets= loadPendingTickets(cxt);
        prevTickets.add(ticket);
        Gson gson= new Gson();
        String json= gson.toJson(prevTickets);
        SharedPreferences sharedPreferences= cxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("savedTickets",json);
        editor.apply();
    }

    public static ArrayList<Ticket> loadPendingTickets(Context cxt){
        SharedPreferences sharedPreferences= cxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String s=sharedPreferences.getString("savedTickets", null);
        Gson gson= new Gson();
        Type type = new TypeToken<ArrayList<Ticket>>() {}.getType();
        ArrayList<Ticket> tickets= gson.fromJson(s, type);
        if (tickets==null)tickets=new ArrayList<>();
        return tickets;
    }

    public static ArrayList<Ticket> updatePending(Context cxt,ArrayList<Ticket> tickets){
        Gson gson= new Gson();
        String json= gson.toJson(tickets);
        SharedPreferences sharedPreferences= cxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("savedTickets",json);
        editor.apply();
        return loadPendingTickets(cxt);

    }

    public static void bookTickets(Context cxt, ArrayList<Ticket> newTickets){
        ArrayList<Ticket> tickets=loadTickets(cxt);
        tickets.addAll(newTickets);
        Gson gson= new Gson();
        String json= gson.toJson(tickets);
        SharedPreferences sharedPreferences= cxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("bookedTickets",json);
        editor.apply();
    }

    public static ArrayList<Ticket> loadTickets(Context cxt){
        SharedPreferences sharedPreferences= cxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String s=sharedPreferences.getString("bookedTickets", null);
        Gson gson= new Gson();
        Type type = new TypeToken<ArrayList<Ticket>>() {}.getType();
        ArrayList<Ticket> tickets= gson.fromJson(s, type);
        if (tickets==null)tickets=new ArrayList<>();
        return tickets;
    }
    public static ArrayList<Ticket> updateBookedTickets(Context cxt,ArrayList<Ticket> tickets){
        Gson gson= new Gson();
        String json= gson.toJson(tickets);
        SharedPreferences sharedPreferences= cxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("bookedTickets",json);
        editor.apply();
        return loadTickets(cxt);

    }

    public static void buyAllTickets(Context cxt){
        ArrayList<Ticket> tickets= loadPendingTickets(cxt);
        bookTickets(cxt, tickets);
        updatePending(cxt,new ArrayList<Ticket>());
    }
}
