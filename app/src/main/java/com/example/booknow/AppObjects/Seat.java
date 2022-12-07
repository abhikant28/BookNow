package com.example.booknow.AppObjects;

public class Seat {
    private String seatId;
    private boolean reserved;
    private String seatNum;
    private String row;
    private String column;

    public Seat(String seatId, boolean reserved, String seatNum, String row, String column) {
        this.seatId = seatId;
        this.reserved = reserved;
        this.seatNum = seatNum;
        this.row = row;
        this.column = column;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }
}
