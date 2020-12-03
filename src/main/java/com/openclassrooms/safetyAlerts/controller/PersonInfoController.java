package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.PersonInfo;
import com.openclassrooms.safetyAlerts.Interface.IpersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class    PersonInfoController {

        @Autowired
        private IpersonInfoService personInfoService;

        @GetMapping(path = "personInfo")
        public Collection<PersonInfo> getPersonInfo(@RequestParam @Valid String lastName, @RequestParam(required = false) String firstName) {
                return personInfoService.getPersonInfo(lastName, firstName);
        }
}
