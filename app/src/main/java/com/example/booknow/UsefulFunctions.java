package com.example.booknow;

import android.util.Log;

import com.example.booknow.AppObjects.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class UsefulFunctions {

    public static String[] timings=new String[]{"2:00PM - 3:00PM","3:00PM - 4:00PM","4:00PM - 5:00PM",
            "5:00PM - 6:00PM","6:00PM - 7:00PM","7:00PM - 8:00PM","8:00PM - 9:00PM","9:00PM - 10:00PM"};
    public static HashMap<String,Integer> timingsToSlot=new HashMap<>();

    private static boolean eventsDone;
    private static boolean scheduleDone;

    public static void timeSlotHash(){
        int i=0;
        for(String s:timings){
            timingsToSlot.put(s,i);
            i++;
        }
    }

    public static void GetEvents() {
        eventsDone=false;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    eventsDone=false;
                    Getevents();
                } catch (Exception e) {
                    e.printStackTrace
                            ();
                    Log.i("GetRequestRun:::::", "Exception");
                }
            }
        };
        new Thread(runnable).start();
    }

    private static JSONObject Getevents() throws Exception {
        String EVENTS_URL = "https://run.mocky.io/v3/6d4cce57-147c-4916-a4eb-28a239a121d7";
        URL url = new URL(EVENTS_URL);
        String result = "";
        HttpURLConnection urlConnection = null;
        urlConnection = (HttpURLConnection) url.openConnection();

        InputStream in = urlConnection.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);

        int streamData = reader.read();

        while (streamData != -1) {
            char current = (char) streamData;
            result += current;
            streamData = reader.read();
        }
        urlConnection.disconnect();
        getEvents(new JSONObject(result));
        try {
            Log.i(" API: ", " Creation");
            //  Log.i("API RESponse:::::", result);
            return new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(" JSON Object: ", "Malformed JSON");
            return null;
        }

    }

    private static void getEvents(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("events");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsObj = (JSONObject) jsonArray.get(i);
            LandingPage.eventsMap.put(jsObj.getString("id"), new Event(jsObj.getString("id"), jsObj.getString("name"), jsObj.getString("img").split("&&&&"), jsObj.getString("type"), jsObj.getString("about"), jsObj.getString("rating"), jsObj.getString("language"), jsObj.getString("price")));
        }
        eventsDone=true;
//        Log.i("Events Map::::::::::",LandingPage.eventsMap.toString());
        setReserved();

    }

    public static void GetSchedule() {
        scheduleDone=false;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    scheduleDone=false;
                    Getschedule();
                } catch (Exception e) {
                    e.printStackTrace
                            ();
                    Log.i("GetRequestRun:::::", "Exception");
                }
            }
        };
        new Thread(runnable).start();
    }

    private static JSONObject Getschedule() throws Exception {
        String SCHEDULE_URL = "https://run.mocky.io/v3/b073662c-0ea9-4d0f-8365-94d6aee1f686";
        URL url = new URL(SCHEDULE_URL);
        String result = "";
        HttpURLConnection urlConnection = null;
        urlConnection = (HttpURLConnection) url.openConnection();

        InputStream in = urlConnection.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);

        int streamData = reader.read();

        while (streamData != -1) {
            char current = (char) streamData;
            result += current;
            streamData = reader.read();
        }
        urlConnection.disconnect();
        JSONObject response = new JSONObject(result);
        getSchedule(response.getJSONArray("schedule"));
        getSeats(response.getJSONArray("reserved"));
        try {
            Log.i(" API: ", " Creation");
           //
            // Log.i("API RESponse:::::", result);
            return new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(" JSON Object: ", "Malformed JSON");
            return null;
        }
    }

    private static void getSchedule(JSONArray jsonObject) throws JSONException {

        for (int i = 0; i < jsonObject.length(); i++) {
            JSONArray jsArr = (JSONArray) jsonObject.get(i);
            for (int j = 0; j < jsArr.length(); j++) {
                LandingPage.EVENTS_SCHEDULE[i][j] = String.valueOf(jsArr.get(j));
            }
        }
//        Log.i("Schedule::::::::::", Arrays.deepToString(LandingPage.EVENTS_SCHEDULE));
        scheduleDone=true;

    }

    private static void getSeats(JSONArray jsonObject) throws JSONException {

        for (int i = 0; i < jsonObject.length(); i++) {
            JSONArray jsArr = (JSONArray) jsonObject.get(i);
            for (int j = 0; j < jsArr.length(); j++) {
                LandingPage.SEAT_RESERVATIONS[i][j] = String.valueOf(jsArr.get(j));
            }
        }
//        Log.i("Reserved Seats::::", Arrays.deepToString(LandingPage.SEAT_RESERVATIONS));
//        Log.i("EVENT MAP::::::::::", LandingPage.eventsMap.toString());
//        Log.i("Reserved::::::::::", Arrays.deepToString(LandingPage.EVENTS_SCHEDULE));

    }

    public static void setReserved() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    setSeats();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("GetRequestRun:::::", "Exception");
                }
            }
        };
        new Thread(runnable).start();
    }

    private static void setSeats() {
        while(!eventsDone && !scheduleDone){}

//        Log.i("STARTING:::::::", LandingPage.eventsMap.toString());
        for(int i=0;i<7;i++){
            for(int j=0;j<8;j++){
                Event ev=LandingPage.eventsMap.get(LandingPage.EVENTS_SCHEDULE[i][j]);
                    ev.setReservedSeats(i,j,LandingPage.SEAT_RESERVATIONS[i][j]);
                ev.getReservedSeats();
//                Log.i("Event SEAT:::", ev.getId()+"Started "+i+j+ev.getReservedSeats(i,j));
                ev.setSlots(i,timings[j]);
//                    Log.i("Event SEAT:::", "Not Null"+timings[j]);
//                    ev.setTimings();
                    LandingPage.eventsMap.put(ev.getId(),ev);
            }
        }
    }
}
