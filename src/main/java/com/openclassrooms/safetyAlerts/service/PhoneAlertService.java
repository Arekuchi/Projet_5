package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.serviceInterface.IPhoneAlertService;
import com.openclassrooms.safetyAlerts.dao.IFirestationDAO;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class PhoneAlertService implements IPhoneAlertService {

    @Autowired
    private IFirestationDAO firestationDAO;

    @Autowired
    private IPersonDAO personDAO;

    public Collection<String> getPhoneList(String firestationStation) {
        Collection<String> phoneAlertList = new HashSet<>();
        List<Firestation> firestationList = firestationDAO.getFirestationAddressByStation(firestationStation);

        for (Firestation firestation : firestationList) {
            List<Person> personListByAddress = personDAO.getPersonByAddress(firestation.getAddress());
            for (Person person : personListByAddress) {
                phoneAlertList.add(person.getPhone());
            }
        }
        return phoneAlertList;
    }
}
