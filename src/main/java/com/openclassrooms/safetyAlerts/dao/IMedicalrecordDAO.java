package com.openclassrooms.safetyAlerts.dao;


import com.openclassrooms.safetyAlerts.model.Medicalrecord;

import java.util.Collection;

public interface IMedicalrecordDAO {

    boolean createMedicalrecord(Medicalrecord medicalrecord);
    boolean deleteMedicalrecord(Medicalrecord medicalrecord);
    boolean updateMedicalrecord(Medicalrecord medicalrecord);

    Medicalrecord getMedicalRecordByName(String lastName, String firstName);
    Collection<Medicalrecord> getMedicalrecords(String birthdate);
}
