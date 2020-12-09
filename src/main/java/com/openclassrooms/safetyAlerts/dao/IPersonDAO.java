package com.openclassrooms.safetyAlerts.dao;

import com.openclassrooms.safetyAlerts.model.Person;

public interface IPersonDAO {

    boolean createPerson(Person person);
    boolean deletePerson(Person person);
    boolean updatePerson(Person person);
}
