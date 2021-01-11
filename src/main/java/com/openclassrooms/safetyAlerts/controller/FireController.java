package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.Fire;
import com.openclassrooms.safetyAlerts.serviceInterface.IFireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class FireController {

    @Autowired
    IFireService fireService;

    @GetMapping(path = "fire")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Fire> getFire(@RequestParam String address) {
        return fireService.getFire(address);
    }

}
