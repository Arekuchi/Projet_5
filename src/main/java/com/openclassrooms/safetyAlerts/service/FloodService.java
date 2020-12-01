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
import java.util.List;

@Service
public class FloodService implements IFloodService {

    @Autowired
    DataRepository dataRepository;

    @Override
    public Collection<Flood> getFlood(List<String> stations) {
        Collection<Flood> floodCollection = new ArrayList<>();
        List<Firestation> firestationFlood = dataRepository.getFirestationAddressByStationList(stations);
        System.out.println("La liste contient : " + firestationFlood);


        for (Firestation firestation : firestationFlood) {
            List<Person> personListByAddress = dataRepository.getPersonByAddress(firestation.getAddress());
            System.out.println("Inside 1st for - void list");

            for (Person person : personListByAddress) {
                System.out.println("Inside 2nd for - empty");
                Flood flood = new Flood();
                flood.setFirstName(person.getFirstName());
                flood.setLastName(person.getLastName());
                flood.setPhone(person.getPhone());
                flood.setAddress(person.getAddress());

                System.out.println("Should return person");

                Medicalrecord medicalrecordFlood = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
                flood.setMedications(medicalrecordFlood.getMedications());
                flood.setAllergies(medicalrecordFlood.getAllergies());
                flood.setAge(CalculateAge.calculateAge(medicalrecordFlood.getBirthdate()));

                System.out.println("Should return medical records");

                floodCollection.add(flood);


            }

        }
        System.out.println(firestationFlood);
        return floodCollection;
    }

}
