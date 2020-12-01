package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.Interface.IPhoneAlertService;
import com.openclassrooms.safetyAlerts.dao.Firestation;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class PhoneAlertService implements IPhoneAlertService {

    @Autowired
    private DataRepository dataRepository;

    public Collection<String> getPhoneList(String firestationStation) {
        Collection<String> phoneAlertList = new HashSet<>();
        List<Firestation> firestationList = dataRepository.getFirestationAddressByStation(firestationStation);

        for (Firestation firestation : firestationList) {
            List<Person> personListByAddress = dataRepository.getPersonByAddress(firestation.getAddress());
            for (Person person : personListByAddress) {
                phoneAlertList.add(person.getPhone());
            }
        }

        return phoneAlertList;
    }
}
