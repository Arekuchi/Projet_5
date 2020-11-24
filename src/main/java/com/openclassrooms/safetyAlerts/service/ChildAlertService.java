package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.ChildAlert;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ChildAlertService implements IChildAlertService {

    @Autowired
    DataRepository dataRepository;

    @Override
    public Collection<ChildAlert> getChildAlert(String address) {
        Collection<ChildAlert> childAlertCollection = new ArrayList<>();
        Collection<Person> personList = dataRepository.getPersonByAddress(address);

        for (Person person : personList) {
            ChildAlert childAlert = new ChildAlert();
            childAlert.setFirstName(person.getFirstName());
            childAlert.setLastName(person.getLastName());

            Medicalrecord medicalrecordChild = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            childAlert.setAge(CalculateAge.calculateAge(medicalrecordChild.getBirthdate()));


            childAlertCollection.add(childAlert);
        }
        // faire un condition, IF au moins 1 personne age>18, THEN return childAlertCollection, ELSE return null
        return childAlertCollection;
    }
}
