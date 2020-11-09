package com.openclassrooms.safetyAlerts.dao;

public class Firestation {
    private String address;
    private String station;

    public String getAddress(){
        return address;
    }
    public void setAddress(String input){
        this.address = input;
    }
    public String getStation(){
        return station;
    }
    public void setStation(String input){
        this.station = input;
    }
}
