package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.Fire;
import com.openclassrooms.safetyAlerts.service.IFireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class FireController {

    @Autowired
    IFireService fireService;

    @GetMapping(path = "fire")
    public Collection<Fire> getFire(@RequestParam String address) {
        return fireService.getFire(address);
    }

}
