package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.serviceInterface.ICommunityEmail;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashSet;

@Service
public class CommunityEmail implements ICommunityEmail {

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public Collection<String> getCommunityEmail(String city) {
        Collection<String> personEmail = new HashSet<String>();
        if (city.isEmpty()) {
            throw new InvalidArgumentException("La ville ne peut être vide");
        }
        for (Person person : personDAO.getPersonsByCity(city)) {
            personEmail.add(person.getEmail());
        }
        return personEmail;
    }
}
