package com.openclassrooms.safetyAlerts.dto;

import java.util.ArrayList;
import java.util.List;

public class Fire {
    private String firstName;
    private String lastName;
    private String phone;
    private List<String> medications = new ArrayList<String>();
    private List<String> allergies = new ArrayList<String>();
    private int age;
    private String station;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) { this.medications = medications; }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
