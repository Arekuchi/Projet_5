package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.Interface.IPhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PhoneAlertController {

    @Autowired
    IPhoneAlertService phoneAlertService;

    @GetMapping(path = "phoneAlertList")
    public Collection<String> getPhoneList(@RequestParam String firestation) {
        return phoneAlertService.getPhoneList(firestation);
    }
}
