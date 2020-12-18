package com.openclassrooms.safetyAlerts.dao;


import com.openclassrooms.safetyAlerts.model.Medicalrecord;

public interface IMedicalrecordDAO {

    boolean createMedicalrecord(Medicalrecord medicalrecord);
    boolean deleteMedicalrecord(Medicalrecord medicalrecord);
    boolean updateMedicalrecord(Medicalrecord medicalrecord);

    Medicalrecord getMedicalRecordByName(String lastName, String firstName);
}
