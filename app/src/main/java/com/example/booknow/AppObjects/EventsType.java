package com.example.booknow.AppObjects;

public class EventsType {

    private String name;
    private int quantity;
    private String id;
    private int image;

    public EventsType(String name, int quantity, int image, String id) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }
}
