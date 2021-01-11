package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.dao.IMedicalrecordDAO;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class MedicalrecordServiceImpl implements IMedicalrecordService {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    IMedicalrecordDAO medicalrecordDAO;

    @Override
    public boolean createMedicalrecord(Medicalrecord medicalrecord) {

        if (!dataRepository.getDatabase().getMedicalrecords().contains(medicalrecord)) {
            medicalrecordDAO.createMedicalrecord(medicalrecord);
            return true;
        }


        else {
            throw new DataAlreadyExistException("Le medicalrecord de " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " existe déjà. ");
        }
    }

    @Override
    public boolean deleteMedicalrecord(Medicalrecord medicalrecord) {

        if (!medicalrecordDAO.deleteMedicalrecord(medicalrecord)) {
            throw new DataNotFoundException("Le medicalrecord de " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " n'existe pas. ");
        }
        return true;
    }

    @Override
    public boolean updateMedicalrecord(Medicalrecord medicalrecord) {

        if (!medicalrecordDAO.updateMedicalrecord(medicalrecord)) {
            throw new DataNotFoundException("Le medicalrecord de " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " n'existe pas. ");
        }
        return true;
    }
}
