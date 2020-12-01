package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.dao.Firestation;
import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FirestationService implements IFirestationService{

    @Autowired
    private DataRepository dataRepository;

    @Override
    public Collection<FirestationDTO> getFirestationDTO(String stationNumber) {
        Collection<FirestationDTO> firestationDTOCollection = new ArrayList<>();
        List<Firestation> firestationList = dataRepository.getFirestationAddressByStation(stationNumber);

        int adultCount = 0;
        int childCount = 0;

        for (Firestation firestation : firestationList) {
            List<Person> personListByAddress = dataRepository.getPersonByAddress(firestation.getAddress());

            for (Person person : personListByAddress) {
                FirestationDTO firestationDTO = new FirestationDTO();
                firestationDTO.setFirstName(person.getFirstName());
                firestationDTO.setLastName(person.getLastName());
                firestationDTO.setAddress(person.getAddress());
                firestationDTO.setPhone(person.getPhone());

                Medicalrecord medicalrecordFire = dataRepository.getMedicalRecordByName(person.getLastName(), person.getFirstName());
                int age = CalculateAge.calculateAge(medicalrecordFire.getBirthdate());
                firestationDTO.setAge(age);

                if (age <= 18)
                    childCount++;
                else
                    adultCount++;

                firestationDTO.setAdult(adultCount);
                firestationDTO.setChild(childCount);

                firestationDTOCollection.add(firestationDTO);
            }

        }

        return firestationDTOCollection;
    }


    //Firestation firestation = dataRepository.getFirestationByStation(stationNumber);
    /*List<String> firestationAddress = new ArrayList<>();
        while (firestation.getAddress() != null) {
            firestationAddress.add(firestation.getAddress());
            if (firestationAddress.contains(firestation.getAddress())) {
                break;
            }
        }
        Collection<Person> personList = dataRepository.getPersonByAddress(address);
        int adult = 0; // firestationDTO.setAdult 0 >> i++
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

        */

    //String address = firestation.getAddress();

}
