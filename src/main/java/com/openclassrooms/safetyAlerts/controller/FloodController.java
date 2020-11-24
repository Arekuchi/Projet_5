package com.openclassrooms.safetyAlerts.controller;

import com.openclassrooms.safetyAlerts.dto.Flood;
import com.openclassrooms.safetyAlerts.service.IFloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class FloodController {

    @Autowired
    IFloodService floodService;

    @GetMapping(path = "flood/stations")
    public Collection<Flood> getFlood(@RequestParam @Valid String station) {
        return floodService.getFlood(station);
    }
}
