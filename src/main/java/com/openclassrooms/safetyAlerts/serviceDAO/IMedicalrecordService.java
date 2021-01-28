package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.model.Medicalrecord;


public interface IMedicalrecordService {

    boolean createMedicalrecord(Medicalrecord medicalrecord);
    boolean deleteMedicalrecord(Medicalrecord medicalrecord);
    boolean updateMedicalrecord(Medicalrecord medicalrecord);
}
