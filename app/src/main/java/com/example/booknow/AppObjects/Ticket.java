package com.example.booknow.AppObjects;

import com.example.booknow.UsefulFunctions;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Ticket {
    private String id;
    private String day;
    private String date;
    private String[] seats;
    private String[] seatNums;
    private String slot;
    private String eventId;
    private String timings;
    private String eventName;
    private String totalAmount;
    private String eventType;
    public String status;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Ticket(String type, String amount, String eventName, String id, String day, String[] seats, String slot, String eventId) {
        this.id = id;
        this.totalAmount=amount;
        this.day = day;
        this.seats = seats;
        this.slot = slot;
        this.eventName=eventName;
        this.eventId=eventId;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, Integer.parseInt(day));
        this.date= c.get(Calendar.DATE)+"_"+new DateFormatSymbols().getMonths()[(c.get(Calendar.MONTH))].substring(0,3);
        this.timings= UsefulFunctions.timings[Integer.parseInt(slot)];
        this.seatNums=new String[seats.length];
        for(int i=0;i<seats.length;i++){
            this.seatNums[i]=String.valueOf((char)(151-Integer.parseInt(seats[i].trim()))/15)+String.valueOf((char)((Integer.parseInt(seats[i].trim())+1)%15!=0?((Integer.parseInt(seats[i].trim())+1)%15):15));
        }
        this.eventType=type;
        this.status="BOOKED";
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getSeats() {
        return seats;
    }

    public void setSeats(String[] seats) {
        this.seats = seats;
    }

    public String[] getSeatNums() {
        return seatNums;
    }

    public void setSeatNums(String[] seatNums) {
        this.seatNums = seatNums;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public int getQuant() {
        return seats.length;
    }
}
