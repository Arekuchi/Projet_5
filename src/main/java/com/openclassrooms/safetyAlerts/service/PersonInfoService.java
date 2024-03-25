package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.serviceInterface.IPersonInfoService;
import com.openclassrooms.safetyAlerts.dao.IMedicalrecordDAO;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonInfoService implements IPersonInfoService {

    @Autowired
    IPersonDAO personDAO;

    @Autowired
    IMedicalrecordDAO medicalrecordDAO;

    @Autowired
    private Person person;

    @Override
    public Collection<PersonInfo> getPersonInfo(String lastName, String firstName) {
        Collection<PersonInfo> personInfoCollection = new ArrayList<>();
        List<Person> personList = personDAO.getPersonsByName(lastName, firstName);
        if (lastName.isEmpty() && firstName.isEmpty()) {
            throw new InvalidArgumentException("Le nom ne peut être vide");
        }
        for (Person person : personList) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setFirstName(person.getFirstName());
            personInfo.setLastName(person.getLastName());
            personInfo.setAddress(person.getAddress());
            personInfo.setEmail(person.getEmail());

            Medicalrecord medicalrecordPerson = medicalrecordDAO.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            personInfo.setMedications(medicalrecordPerson.getMedications());
            personInfo.setAllergies(medicalrecordPerson.getAllergies());
            personInfo.setAge(CalculateAge.calculateAge(medicalrecordPerson.getBirthdate()));
            // si on veut rajouter la date de naissance personInfo.setBirthdate(medicalrecordPerson.getBirthdate());

            personInfoCollection.add(personInfo);
        }
        return personInfoCollection;
    }

    @Override
    public Collection<PersonInfo> getPersonInfo2(String lastname, String firstname) {
        Collection<Person> collectionPerson = person.collectionPerson(lastname, firstname);
        Collection<PersonInfo> personInfoCollection = new ArrayList<>();
        for (Person person : collectionPerson) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setFirstName(person.getFirstName());
            personInfo.setLastname(person.getLastName());
            personInfo.setAddress(person.getAddress());
            personInfo.setEmail(person.getEmail());
            personInfoCollection.add(personInfo);
        }
        return personInfoCollection;
    }
}

