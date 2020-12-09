package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.serviceDAO.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    // Creation d'une personne
    @PostMapping(path="person")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@RequestBody @Valid Person person) {
        personService.createPerson(person);
    }

    // Delete = HttpStatus.RESET_CONTENT
    @DeleteMapping(path="person")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deletePerson(@RequestBody @Valid Person person) {
        personService.deletePerson(person);
    }

    // Update = HttpStatus.NO_CONTENT
    @PutMapping(path="person")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@RequestBody @Valid Person person) {
        personService.updatePerson(person);
    }
}
