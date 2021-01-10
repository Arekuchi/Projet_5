package com.openclassrooms.safetyAlerts.model;


import javax.validation.constraints.NotBlank;
import java.util.Objects;

//création d'un modèle de "personne" pour l'utilisation des données json
public class Person {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String input){
        this.firstName = input;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String input){
        this.lastName = input;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String input){
        this.address = input;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String input){
        this.city = input;
    }
    public String getZip(){
        return zip;
    }
    public void setZip(String input){
        this.zip = input;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String input){
        this.phone = input;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String input){
        this.email = input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}

