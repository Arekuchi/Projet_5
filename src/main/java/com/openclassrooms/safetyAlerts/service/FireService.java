package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.serviceInterface.IFireService;
import com.openclassrooms.safetyAlerts.dao.IFirestationDAO;
import com.openclassrooms.safetyAlerts.dao.IMedicalrecordDAO;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.dto.Fire;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FireService implements IFireService {

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private IMedicalrecordDAO medicalrecordDAO;

    @Autowired
    private IFirestationDAO firestationDAO;

    @Override
    public Collection<Fire> getFire(String address) {
        Collection<Fire> fireCollection = new ArrayList<>();
        Collection<Person> personList = personDAO.getPersonByAddress(address);
        if (address.isEmpty()) {
            throw new InvalidArgumentException("L'addresse ne peut être vide");
        }

        for (Person person : personList) {
            Fire fire = new Fire();
            fire.setFirstName(person.getFirstName());
            fire.setLastName(person.getLastName());
            fire.setPhone(person.getPhone());

            Medicalrecord medicalrecordFire = medicalrecordDAO.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            fire.setMedications(medicalrecordFire.getMedications());
            fire.setAllergies(medicalrecordFire.getAllergies());
            fire.setAge(CalculateAge.calculateAge(medicalrecordFire.getBirthdate()));

            Firestation firestation = firestationDAO.getFirestationByAddress(address);
            fire.setStation(firestation.getStation());

            fireCollection.add(fire);
        }
        return fireCollection;
    }
}
