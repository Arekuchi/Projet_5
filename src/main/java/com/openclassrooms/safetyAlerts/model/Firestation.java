package com.openclassrooms.safetyAlerts.model;

import java.util.Objects;

public class Firestation {
    private String address;
    private String station;

    public String getAddress() {
        return address;
    }

    public void setAddress(String input) {
        this.address = input;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String input) {
        this.station = input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Firestation)) return false;
        Firestation that = (Firestation) o;
        return Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }
}
