package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class PersonInfoService implements IpersonInfoService {

    @Autowired
    private DataRepository dataRepository;



    //rajouter personinfo age
    @Override
    public Collection<PersonInfo> getPersonInfo(String lastName, String firstName) {
        Collection<PersonInfo> personInfoCollection = new ArrayList<>();
        List<Person> personList = dataRepository.getPersonsByName(lastName,firstName);
        for (Person person : personList) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setFirstName(person.getFirstName());
            personInfo.setLastName(person.getLastName());
            personInfo.setAddress(person.getAddress());
            personInfo.setEmail(person.getEmail());


            Medicalrecord medicalrecordPerson = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            personInfo.setMedications(medicalrecordPerson.getMedications());
            personInfo.setAllergies(medicalrecordPerson.getAllergies());
            personInfo.setBirthdate(medicalrecordPerson.getBirthdate()); // a convertir en âge
            personInfo.setAge(CalculateAge.calculateAge(personInfo.getBirthdate()));


            personInfoCollection.add(personInfo);
        }
        return personInfoCollection;
    }
}

