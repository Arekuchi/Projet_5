package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.Interface.ICommunityEmail;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
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
        for (Person person : personDAO.getPersonsByCity(city)) {
            personEmail.add(person.getEmail());
        }
        return personEmail;
    }
}
