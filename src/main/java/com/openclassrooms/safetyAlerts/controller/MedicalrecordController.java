package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dao.IFirestationDAO;
import com.openclassrooms.safetyAlerts.dao.IMedicalrecordDAO;
import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.serviceDAO.IMedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class MedicalrecordController {

    @Autowired
    private IMedicalrecordService medicalrecordService;

    // Creation d'une firestation
    @PostMapping(path="medicalrecord")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMedicalrecord(@RequestBody @Valid Medicalrecord medicalrecord) {
        medicalrecordService.createMedicalrecord(medicalrecord); }

    // Delete = HttpStatus.RESET_CONTENT
    @DeleteMapping(path="medicalrecord")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteMedicalrecord(@RequestBody @Valid Medicalrecord medicalrecord) {
        medicalrecordService.deleteMedicalrecord(medicalrecord);
    }

    // Update = HttpStatus.NO_CONTENT
    @PutMapping(path="medicalrecord")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMedicalrecord(@RequestBody @Valid Medicalrecord medicalrecord) {
        medicalrecordService.updateMedicalrecord(medicalrecord);
    }
}
