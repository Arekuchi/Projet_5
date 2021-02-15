package com.openclassrooms.safetyAlerts.dao;


import com.openclassrooms.safetyAlerts.model.Database;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonDAO implements IPersonDAO {

    @Autowired
    DataRepository dataRepository;

    @Override
    public boolean createPerson(Person person) {
        // ajout de la nouvelle personne en mémoire (Object JAVA)
        dataRepository.getAllPersons().add(person);
        // commit pour appliquer les changements sur le json
        dataRepository.commit();

        return true;
    }

    @Override
    public boolean deletePerson(Person person) {
        // suppression de la personne en mémoire -(Object JAVA)
        boolean result=dataRepository.getAllPersons().remove(person);
        // commit
        dataRepository.commit();

        return result;
    }

    @Override
    public boolean updatePerson(Person person) {
        //
        if (dataRepository.getAllPersons().remove(person)) {
            createPerson(person);
            return true;
        }
        return false;
    }

    @Override
    public List<Person> getPersons() {
        return dataRepository.getDatabase().getPersons();
    }

    @Override
    public Collection<Person> getPersonsByCity(String city) {
        Collection<Person> personCollection = new ArrayList<Person>();
        Database db = dataRepository.getDatabase();
        for (Person person : db.getPersons()) {
            if (person.getCity().equalsIgnoreCase(city)) {
                personCollection.add(person);
            }
        }
        return personCollection;
    }


    @Override
    public List<Person> getPersonsByName(String lastName, String firstName) {
        List<Person> personList = new ArrayList<>(); //création d'une liste vide
        Database db = dataRepository.getDatabase(); //rempli en mémoire le json
        for (Person person : db.getPersons()) {
            if (lastName == null || (person.getLastName().equalsIgnoreCase(lastName)) && (firstName == null || person.getFirstName().equalsIgnoreCase(firstName))) {
                personList.add(person);
            }
        }
        return personList;
    }

    @Override
    public List<Person> getPersonByAddress(String address) {
        List<Person> personList = new ArrayList<>();
        Database db = dataRepository.getDatabase();
        for (Person person : db.getPersons()) {
            if (person.getAddress().equalsIgnoreCase(address)) {
                personList.add(person);
            }
        }
        return personList;
    }
}
