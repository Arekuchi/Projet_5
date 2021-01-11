package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import com.openclassrooms.safetyAlerts.serviceInterface.IFirestationService;
import com.openclassrooms.safetyAlerts.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class FirestationController {

    @Autowired
    private IFirestationService firestationService;

    @Autowired
    private com.openclassrooms.safetyAlerts.serviceDAO.IFirestationService firestationServiceImpl;

    @GetMapping(path = "firestation")
    public Collection<FirestationDTO> getFirestationDTO(@RequestParam String stationNumber) {
        return firestationService.getFirestationDTO(stationNumber);
    }

    // Creation d'une firestation
    @PostMapping(path="firestation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFirestation(@RequestBody @Valid Firestation firestation) { firestationServiceImpl.createFirestation(firestation); }

    // Delete = HttpStatus.RESET_CONTENT
    @DeleteMapping(path="firestation")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteFirestation(@RequestBody @Valid Firestation firestation) {
        firestationServiceImpl.deleteFirestation(firestation);
    }

    // Update = HttpStatus.NO_CONTENT
    @PutMapping(path="firestation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFirestation(@RequestBody @Valid Firestation firestation) {
        firestationServiceImpl.updateFirestation(firestation);
    }
}
