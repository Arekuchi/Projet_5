package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Firestation;
import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.Flood;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FloodService implements IFloodService{

    @Autowired
    DataRepository dataRepository;

    @Override
    public Collection<Flood> getFlood(String station) {
        Collection<Flood> floodCollection = new ArrayList<>();
        Firestation firestation = dataRepository.getFirestationByStation(station);
        String address = firestation.getAddress();
        Collection<Person> personList = dataRepository.getPersonByAddress(address);

        for (Person person : personList) {
            Flood flood = new Flood();
            flood.setFirstName(person.getFirstName());
            flood.setLastName(person.getLastName());
            flood.setPhone(person.getPhone());
            flood.setAddress(person.getAddress());

            Medicalrecord medicalrecordFlood = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            flood.setAge(CalculateAge.calculateAge(medicalrecordFlood.getBirthdate()));

            floodCollection.add(flood);


        }
        return floodCollection;
    }
}
