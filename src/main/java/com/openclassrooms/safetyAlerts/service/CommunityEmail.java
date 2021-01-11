package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.serviceInterface.ICommunityEmail;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;
import java.util.HashSet;


public class CommunityEmail implements ICommunityEmail {

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public Collection<String> getCommunityEmail(String city) {
        Collection<String> personEmail = new HashSet<String>();
        for (Person person : personDAO.getPersonsByCity(city)) {
            personEmail.add(person.getEmail());
        }
        return personEmail;
    }
}
