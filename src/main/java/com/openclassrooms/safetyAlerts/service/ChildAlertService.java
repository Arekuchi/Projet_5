package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.serviceInterface.IChildAlertService;
import com.openclassrooms.safetyAlerts.dao.IMedicalrecordDAO;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.dto.ChildAlert;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.openclassrooms.safetyAlerts.utility.CalculateAge.calculateAge;

@Service
public class ChildAlertService implements IChildAlertService {

    @Autowired
    IPersonDAO personDAO;
    @Autowired
    IMedicalrecordDAO medicalrecordDAO;

    @Override
    public Collection<ChildAlert> getChildAlert(String address) {
        Collection<ChildAlert> childAlertCollection = new ArrayList<>();
        Collection<Person> personList = personDAO.getPersonByAddress(address);
        int childCount = 0;
        if (address.isEmpty()) {
            throw new InvalidArgumentException("L'adresse ne peut être vide");
        }

        for (Person person : personList) {
            ChildAlert childAlert = new ChildAlert();
            childAlert.setFirstName(person.getFirstName());
            childAlert.setLastName(person.getLastName());

            Medicalrecord medicalrecordChild = medicalrecordDAO.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            childAlert.setAge(CalculateAge.calculateAge(medicalrecordChild.getBirthdate()));
            int age = childAlert.getAge();
            if (age <= 18)
                childCount++;

            childAlertCollection.add(childAlert);
        }
        // faire un condition, IF au moins 1 personne age>18, THEN return childAlertCollection, ELSE return null
        if (childCount>1)
        return childAlertCollection;
        else
            childAlertCollection.clear();
        return childAlertCollection;
    }
}
