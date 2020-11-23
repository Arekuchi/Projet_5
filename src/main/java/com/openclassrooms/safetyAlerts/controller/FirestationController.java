package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.FirestationDTO;
import com.openclassrooms.safetyAlerts.service.IFirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class FirestationController {

    @Autowired
    IFirestationService firestationService;

    @GetMapping(path = "firestation")
    public Collection<FirestationDTO> getFirestationDTO(@RequestParam String stationNumber) {
        return firestationService.getFirestationDTO(stationNumber);
    }
}
