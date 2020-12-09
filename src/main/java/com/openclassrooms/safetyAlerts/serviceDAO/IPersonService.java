package com.openclassrooms.safetyAlerts.serviceDAO;


import com.openclassrooms.safetyAlerts.model.Person;

public interface IPersonService {

    boolean createPerson(Person person);
    boolean deletePerson(Person person);
    boolean updatePerson(Person person);
}
