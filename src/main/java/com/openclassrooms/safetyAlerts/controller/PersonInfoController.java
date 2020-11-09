package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.service.IPersonService;
import com.openclassrooms.safetyAlerts.service.IpersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PersonInfoController {

        //On appelle un service pour remonter les infos dont on a besoin pour les réponses d'API
        @Autowired
        private IpersonInfoService personInfoService;

        @GetMapping(path = "personInfo")
        public Collection<String> getPersonInfo(@RequestParam String birthdate) {
            return personInfoService.getPersonInfo(birthdate);
        }
}
