package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Firestation;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.PhoneAlert;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Service
public class PhoneAlertService implements IPhoneAlertService {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public Collection<PhoneAlert> getPhoneAlert(String firestationStation) {
        Collection<PhoneAlert> phoneAlertList = new ArrayList<>();
        Firestation firestation = dataRepository.getFirestationByStation(firestationStation);
        String address = firestation.getAddress();
        Collection<Person> personList = dataRepository.getPersonByAddress(address);

        for (Person person : personList) {
            PhoneAlert phoneAlert = new PhoneAlert();
            phoneAlert.setPhone(person.getPhone());

            phoneAlertList.add(phoneAlert);
        }
        return phoneAlertList;
    }

    public Collection<String> getPhoneList(String firestationStation) {
        Collection<String> phoneAlertList = new HashSet<>();
        Firestation firestation = dataRepository.getFirestationByStation(firestationStation);
        String address = firestation.getAddress();
        Collection<Person> personList = dataRepository.getPersonByAddress(address);

        for (Person person : personList) {
            phoneAlertList.add(person.getPhone());
        }
        return phoneAlertList;
    }
}
