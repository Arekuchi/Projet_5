package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.serviceInterface.ICommunityEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

//Le controller va exposer les API REST pour gérer les requêtes d'un client Web
@RestController
public class CommunityEmailController {
    //On appelle un service pour remonter les infos dont on a besoin pour les réponses d'API
    @Autowired
    private ICommunityEmail personService;

    @GetMapping(path = "communityEmail")
    @ResponseStatus(HttpStatus.OK)
    public Collection<String> getCommunityEmail(@RequestParam String city) {
        return personService.getCommunityEmail(city);
    }
}
