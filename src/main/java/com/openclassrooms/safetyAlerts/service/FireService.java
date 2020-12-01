package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.Interface.IFireService;
import com.openclassrooms.safetyAlerts.dao.Firestation;
import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.Fire;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FireService implements IFireService {

    @Autowired
    private DataRepository dataRepository;


    //ne renvoir que les numéris lié à l'addresse   748 Townings Dr
    @Override
    public Collection<Fire> getFire(String address) {
        Collection<Fire> fireCollection = new ArrayList<>();
        Collection<Person> personList = dataRepository.getPersonByAddress(address);

        for (Person person : personList) {
            Fire fire = new Fire();
            fire.setFirstName(person.getFirstName());
            fire.setLastName(person.getLastName());
            fire.setPhone(person.getPhone());

            Medicalrecord medicalrecordFire = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            fire.setMedications(medicalrecordFire.getMedications());
            fire.setAllergies(medicalrecordFire.getAllergies());
            fire.setAge(CalculateAge.calculateAge(medicalrecordFire.getBirthdate()));

            Firestation firestation = dataRepository.getFirestationByAddress(address);
            fire.setStation(firestation.getStation());

            fireCollection.add(fire);
        }
        return fireCollection;
    }
}
