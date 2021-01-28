package com.openclassrooms.safetyAlerts.model;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Firestation {
    @NotBlank
    private String address;
    @NotBlank
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


    public Firestation() {
        // construteur vide
    }

    public Firestation(@NotBlank String address, @NotBlank String station) {
        this.address = address;
        this.station = station;
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
