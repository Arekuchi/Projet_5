package com.openclassrooms.safetyAlerts.dao;

import com.openclassrooms.safetyAlerts.model.Database;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MedicalRecordDAO implements IMedicalrecordDAO {

    @Autowired
    DataRepository dataRepository;


    @Override
    public boolean createMedicalrecord(Medicalrecord medicalrecord) {

        dataRepository.getDatabase().getMedicalrecords().add(medicalrecord);

        dataRepository.commit();

        return true;
    }

    @Override
    public boolean deleteMedicalrecord(Medicalrecord medicalrecord) {
        boolean result=dataRepository.getDatabase().getMedicalrecords().remove(medicalrecord);
        // commit
        dataRepository.commit();

        return result;
    }

    @Override
    public boolean updateMedicalrecord(Medicalrecord medicalrecord) {
        if (dataRepository.getDatabase().getMedicalrecords().remove(medicalrecord)) {
            createMedicalrecord(medicalrecord);
            return true;
        }
        return false;
    }

    @Override
    public Medicalrecord getMedicalRecordByName(String lastName, String firstName) {
        Medicalrecord medicalrecordInfo = new Medicalrecord();
        Database db = dataRepository.getDatabase();
        for (Medicalrecord medicalrecord : db.getMedicalrecords()) {
            if (medicalrecord.getLastName().equalsIgnoreCase(lastName) && medicalrecord.getFirstName().equalsIgnoreCase(firstName)) {
                medicalrecordInfo = medicalrecord;
            }
        }
        return medicalrecordInfo;
    }

    @Override
    public Collection<Medicalrecord> getMedicalrecords(String birthdate) {
        Collection<Medicalrecord> medicalrecordCollection = new ArrayList<>();
        Database db = dataRepository.getDatabase();
        for (Medicalrecord medicalrecord : db.getMedicalrecords()) {
            if (medicalrecord.getBirthdate().equalsIgnoreCase(birthdate)) {
                medicalrecordCollection.add(medicalrecord);
            }
        }
        return medicalrecordCollection;
    }
}
