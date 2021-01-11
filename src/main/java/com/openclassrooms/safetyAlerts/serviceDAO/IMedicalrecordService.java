package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import org.springframework.stereotype.Service;

@Service
public interface IMedicalrecordService {

    boolean createMedicalrecord(Medicalrecord medicalrecord);
    boolean deleteMedicalrecord(Medicalrecord medicalrecord);
    boolean updateMedicalrecord(Medicalrecord medicalrecord);
}
