package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.PhoneAlert;
import com.openclassrooms.safetyAlerts.service.IPhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PhoneAlertController {

    @Autowired
    IPhoneAlertService phoneAlertService;

    @GetMapping(path = "phoneAlert")
    public Collection<PhoneAlert> getPhoneAlert(@RequestParam String firestation) {
        return phoneAlertService.getPhoneAlert(firestation);
    }
}
