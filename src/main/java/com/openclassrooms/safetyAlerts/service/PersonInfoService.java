package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class PersonInfoService implements IpersonInfoService {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public Collection<String> getPersonInfo(String birthdate) {
        Collection<String> personInfo = new HashSet<String>();
        for (Medicalrecord person : dataRepository.getMedicalrecords(birthdate)) {
            personInfo.add(person.getFirstName() + person.getLastName() + person.getBirthdate() + person.getMedications() + person.getAllergies());
        }
        for (Person person: dataRepository.getPersonsByCity(birthdate)) {
            personInfo.add(person.getAddress() + person.getEmail());
        }
        return personInfo;
    }
}

