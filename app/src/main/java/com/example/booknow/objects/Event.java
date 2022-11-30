package com.example.booknow.objects;

public class Event {

    private String date;
    private String id;
    private String name;
    private String[] img;
    private String type;
    private String status;
    private String about;
    private String tag;
    private String startTime;
    private String endTime;
    private boolean saved;
    private boolean booked;
    private String rating;
    private String trailerURL;
    private String language;
    private String certification;

    public Event(String date, String id, String name, String[] img, String type, String status, String about, String tag, String startTime, String endTime) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
        this.status = status;
        this.about = about;
        this.tag = tag;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
