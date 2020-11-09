package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import com.openclassrooms.safetyAlerts.service.IPersonService;
import com.openclassrooms.safetyAlerts.service.IpersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;


//Le controller va exposer les API REST pour gérer les requêtes d'un client Web
@RestController
public class PersonInfoController {

    //On appelle un service pour remonter les infos dont on a besoin pour les réponses d'API
    @Autowired
    private IpersonInfoService personInfoService;

    @GetMapping(path = "personInfo")
    public Collection<String> getPersonInfo(@RequestParam String birthdate) {
        return personInfoService.getPersonInfo(birthdate);
    }


    @GetMapping(path = "personInfo")
    public Collection<PersonInfo> getPersonInfo(@RequestParam @Valid String lastname, @RequestParam(required = false) String firstname) {
        return personInfoService.getPersonInfo2(lastname, firstname);
    }
}
