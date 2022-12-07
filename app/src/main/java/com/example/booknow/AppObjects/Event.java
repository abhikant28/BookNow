package com.example.booknow.AppObjects;


import java.util.ArrayList;

public class Event {

    private String date;
    private String id;
    private String name;
    private String[] img;
    private String type;
    private String about;
    //    private String[][] timings=new String[7][8];
    private ArrayList[] slots = new ArrayList[7];
    private boolean saved;
    private boolean booked;
    private String rating;
    private String language;
    private String duration = "1H";
    private String price;
    private String[][] reservedSeats = new String[7][8];

    public ArrayList getSlots(int i) {
        return this.slots[i];
    }

    public ArrayList[] getSlots() {
        return slots;
    }

    public void setSlots(ArrayList[] slots) {
        this.slots = slots;
    }

    public void setSlots(int i, int j, String slots) {
        this.slots[i].set(j, slots);
    }

    public void setSlots(int i, String slots) {
        if (this.slots[i] == null) this.slots[i] = new ArrayList<String>();
        this.slots[i].add(slots);
    }

    public String[][] getReservedSeats() {
        return reservedSeats;
    }

    public String getReservedSeats(int i, int j) {
        return reservedSeats[i][j];
    }

    public void setReservedSeats(String[][] reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public void setReservedSeats(int i, int j, String reservedSeats) {
        if (this.reservedSeats == null) this.reservedSeats = new String[7][8];
        this.reservedSeats[i][j] = reservedSeats;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public Event(String date, String id, String name, String[] img, String type, String about, boolean saved, String rating, String trailerURL, String language, String certification) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
        this.about = about;
        this.saved = saved;
        this.rating = rating;
        this.language = language;
    }

    public Event(String date, String id, String name, String[] img, String type, String status, String about, String tag, String startTime, String endTime) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
        this.about = about;
    }

    public Event(String id, String name, String[] img, String type, String about, String rating, String language, String price) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
        this.about = about;
        this.rating = rating;
        this.language = language;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
