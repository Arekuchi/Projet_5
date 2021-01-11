package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.ChildAlert;
import com.openclassrooms.safetyAlerts.service.ChildAlertService;
import com.openclassrooms.safetyAlerts.serviceInterface.IChildAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ChildAlertController {

    @Autowired
    ChildAlertService childAlertService;

    @GetMapping(path = "childAlert")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ChildAlert> getChildAlert(@RequestParam String address) {

        return childAlertService.getChildAlert(address);
    }
}
