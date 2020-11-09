package com.openclassrooms.safetyAlerts.dao;


import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

//création d'un modèle de "personne" pour l'utilisation des données json
public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Autowired
    private DataRepository dataRepository;

    //Méthode pour lister les personnes
    public Collection<Person> collectionPerson (String lastname, String firstname) {
        Collection<Person> personCollection = new ArrayList<Person>();
        Database database = dataRepository.getDatabase();
        for (Person person : database.getPersons()) {
            if (lastname == null || (person.getLastName().equalsIgnoreCase(lastname)) && (firstname == null || person.getFirstName().equalsIgnoreCase(firstname))) {
                personCollection.add(person);
            }
        }
        return personCollection;
    }
    // TODO : méthode pour récupéré les MedicalRecord , getMedicalRecordInfo , d'une personne (firstname, lastname)


}

