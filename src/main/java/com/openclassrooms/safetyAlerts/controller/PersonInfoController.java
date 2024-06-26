package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import com.openclassrooms.safetyAlerts.serviceInterface.IPersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class    PersonInfoController {

        @Autowired
        IPersonInfoService personInfoService;

        @GetMapping(path = "personInfo")
        @ResponseStatus(HttpStatus.OK)
        public Collection<PersonInfo> getPersonInfo(@RequestParam @Valid String lastName, @RequestParam(required = false) String firstName) {
                return personInfoService.getPersonInfo(lastName, firstName);
        }
}
