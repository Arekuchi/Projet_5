package com.openclassrooms.safetyAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyAlerts.dao.Database;
import com.openclassrooms.safetyAlerts.dao.Firestation;
import com.openclassrooms.safetyAlerts.dao.Medicalrecord;
import com.openclassrooms.safetyAlerts.dao.Person;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Repository
public class DataRepository {
    // Permets de mapper du json en objet java
    private static final ObjectMapper objectMapper = new ObjectMapper();
    // Cet objet va represetner le json en mémoire JAVA
    private static Database database;

    // Utilise les déclarations ci dessus pour mapper l'objet.
    public DataRepository() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data.json");
        database = objectMapper.readerFor(Database.class).readValue(inputStream);
    }

    //Méthode pour récupéré les persons du data.json
    public Collection<Person> getPersonsByCity(String city) {
        Collection<Person> personCollection = new ArrayList<Person>();
        for (Person person: database.getPersons()) {
            if (person.getCity().equalsIgnoreCase(city)) {
                personCollection.add(person);
            }
        }
        return personCollection;
    }


    // TODO = Faire les méthodes pour récupéré Firestations et Medicalrecords
    public Collection<Firestation> getFirestation(String address) {
        Collection<Firestation> firestationCollection = new ArrayList<>();
        for (Firestation firestation: database.getFirestations()) {
            if (firestation.getAddress().equalsIgnoreCase(address)) {
                firestationCollection.add(firestation);
            }
        }
        return firestationCollection;
    }

    public Collection<Medicalrecord> getMedicalrecords(String birthdate) {
        Collection<Medicalrecord> medicalrecordCollection = new ArrayList<>();
        for (Medicalrecord medicalrecord: database.getMedicalrecords()) {
            if (medicalrecord.getBirthdate().equalsIgnoreCase(birthdate)) {
                medicalrecordCollection.add(medicalrecord);
            }
        }
        return medicalrecordCollection;
    }


    public static void main(String[] args) throws IOException {
        DataRepository dataRepository = new DataRepository();
        System.out.println(dataRepository.database.getPersons().size());
    }

}
