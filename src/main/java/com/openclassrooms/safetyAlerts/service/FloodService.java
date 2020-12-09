package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.Interface.IFloodService;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
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


    // dernier controller qui ne fonctionne pas
    @Override
    public Collection<Flood> getFlood(List<String> stations) {
        Collection<Flood> floodCollection = new ArrayList<>();
        List<Firestation> firestationFlood = dataRepository.getFirestationAddressByStationList(stations);

        for (String stationNumber : stations) {
            // récup les addresses
            List<Firestation> firestationListAddress = dataRepository.getFirestationAddressByStation(stationNumber);

            for (Firestation firestation : firestationListAddress) {

                List<Person> personListByAddress = dataRepository.getPersonByAddress(firestation.getAddress());

                for (Person person : personListByAddress) {
                    Flood flood = new Flood();

                    flood.setFirstName(person.getFirstName());
                    flood.setLastName(person.getLastName());
                    flood.setPhone(person.getPhone());
                    flood.setAddress(person.getAddress());

                    Medicalrecord medicalrecordFlood = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
                    flood.setMedications(medicalrecordFlood.getMedications());
                    flood.setAllergies(medicalrecordFlood.getAllergies());
                    flood.setAge(CalculateAge.calculateAge(medicalrecordFlood.getBirthdate()));

                    floodCollection.add(flood);

                }

            }
        }
        return floodCollection;
    }

}
