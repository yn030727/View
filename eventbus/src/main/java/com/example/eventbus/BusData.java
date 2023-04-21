package com.example.eventbus;

public class BusData {
    public String message;

    public BusData(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
