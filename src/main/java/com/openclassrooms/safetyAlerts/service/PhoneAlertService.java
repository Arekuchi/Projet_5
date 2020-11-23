package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Firestation;
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
    public Collection<String> getPhoneAlert(String firestation) {
        Collection<String> phoneCollection = new HashSet<>();
        Collection<String> firestationList = new ArrayList<>();
        for (String firestation1 : firestationList) {
        PhoneAlert phoneAlert = new PhoneAlert();
            Firestation firestationAddress = dataRepository.getFirestationByAddress(firestation);
        phoneAlert.setAddress(firestationAddress.getAddress());
        if (phoneAlert.getAddress() == firestationAddress.getAddress()) {
            phoneCollection.add(phoneAlert.getPhone());
        }


        }
        return phoneCollection;
    }
}
