package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.serviceInterface.IPhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PhoneAlertController {

    @Autowired
    IPhoneAlertService phoneAlertService;

    @GetMapping(path = "phoneAlertList")
    @ResponseStatus(HttpStatus.OK)
    public Collection<String> getPhoneList(@RequestParam String firestation) {
        return phoneAlertService.getPhoneList(firestation);
    }
}
