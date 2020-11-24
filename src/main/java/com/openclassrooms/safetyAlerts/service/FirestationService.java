package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Firestation;
import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import com.openclassrooms.safetyAlerts.utility.DefineAdultOrChild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FirestationService implements IFirestationService{

    @Autowired
    private DataRepository dataRepository;


    // ne renvoie que les personnes à l'adresse    748 Townings Dr
    // rempli mal le compteur adult/child
    public Collection<FirestationDTO> getFirestationDTO(String stationNumber) {
        Collection<FirestationDTO> firestationDTOCollection = new ArrayList<>();
        Firestation firestation = dataRepository.getFirestationByStation(stationNumber);
        String address = firestation.getAddress();
        Collection<Person> personList = dataRepository.getPersonByAddress(address);
        int adult = 0;
        int child = 0;

        for (Person person : personList) {
            FirestationDTO firestationDTO = new FirestationDTO();
            firestationDTO.setFirstName(person.getFirstName());
            firestationDTO.setLastName(person.getLastName());
            firestationDTO.setAddress(person.getAddress());
            firestationDTO.setPhone(person.getPhone());

            Medicalrecord medicalrecordFire = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
            firestationDTO.setAge(CalculateAge.calculateAge(medicalrecordFire.getBirthdate()));

            
            firestationDTOCollection.add(firestationDTO);
        }
        return firestationDTOCollection;
    }




}
