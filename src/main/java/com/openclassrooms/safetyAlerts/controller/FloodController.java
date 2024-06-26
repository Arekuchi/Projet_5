package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.Flood;
import com.openclassrooms.safetyAlerts.serviceInterface.IFloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class FloodController {

    @Autowired
    IFloodService floodService;

    @GetMapping(path = "flood/stations")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Flood> getFlood(@RequestParam List<String> stations) {
        return floodService.getFlood(stations);
    }
}
