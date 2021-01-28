package com.openclassrooms.safetyAlerts.service;

import com.openclassrooms.safetyAlerts.exceptions.InvalidArgumentException;
import com.openclassrooms.safetyAlerts.serviceInterface.IFirestationService;
import com.openclassrooms.safetyAlerts.dao.IFirestationDAO;
import com.openclassrooms.safetyAlerts.dao.IMedicalrecordDAO;
import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import com.openclassrooms.safetyAlerts.utility.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FirestationService implements IFirestationService {

    @Autowired
    private IFirestationDAO firestationDAO;

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private IMedicalrecordDAO medicalrecordDAO;

    @Override
    public Collection<FirestationDTO> getFirestationDTO(String stationNumber) {
        Collection<FirestationDTO> firestationDTOCollection = new ArrayList<>();
        List<Firestation> firestationList = firestationDAO.getFirestationAddressByStation(stationNumber);
        int adultCount = 0;
        int childCount = 0;

        if (stationNumber.isEmpty()) {
            throw new InvalidArgumentException("Le numéro de station ne peut être vide");
        }

        for (Firestation firestation : firestationList) {
            List<Person> personListByAddress = personDAO.getPersonByAddress(firestation.getAddress());

            for (Person person : personListByAddress) {
                FirestationDTO firestationDTO = new FirestationDTO();
                firestationDTO.setFirstName(person.getFirstName());
                firestationDTO.setLastName(person.getLastName());
                firestationDTO.setAddress(person.getAddress());
                firestationDTO.setPhone(person.getPhone());

                Medicalrecord medicalrecordFire = medicalrecordDAO.getMedicalRecordByName(person.getLastName(), person.getFirstName());
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
}
