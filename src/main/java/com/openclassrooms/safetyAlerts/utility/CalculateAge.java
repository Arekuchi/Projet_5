package com.openclassrooms.safetyAlerts.utility;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class CalculateAge {

    private static LocalDate currentDate = LocalDate.now();

    //méthode public static qui calcule l'âge à partir de la birthdate ATTENTION format m/d/y
    public static int calculateAge(String birthdate) {
        //Instantiating the SimpleDateFormat class
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        //Parsing the given String to Date object
        String date = birthdate;
        LocalDate localDate = LocalDate.parse(date, formatter);
        // calcul convertit en nombre d'année entre la birthdate et la current date
        int age = Period.between(localDate, currentDate).getYears();

        return age;
    }
}
