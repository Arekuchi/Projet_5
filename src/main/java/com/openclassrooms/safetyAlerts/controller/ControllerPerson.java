package com.openclassrooms.safetyAlerts.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


@RestController
public class ControllerPerson {
    @GetMapping("/communityemail")
    public Object communityEmail(@RequestParam String city) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("C:\\Work-Space-IntelliJ\\safetyAlerts\\src\\main\\resources\\data.json")));
            JSONObject jsonAllData = new JSONObject(jsonString);
            JSONArray jsonArrayPersons = jsonAllData.getJSONArray("persons");
            ArrayList emailArrayList = new ArrayList();
            for (int i = 0; i < jsonArrayPersons.length(); i++) { //23
                if (jsonArrayPersons.getJSONObject(i).getString("city").equals(city)) {
                    emailArrayList.add(jsonArrayPersons.getJSONObject(i).getString("email"));
                }

            }
            return emailArrayList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}

