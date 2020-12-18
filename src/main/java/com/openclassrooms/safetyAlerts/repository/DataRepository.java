package com.openclassrooms.safetyAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyAlerts.model.Database;
import com.openclassrooms.safetyAlerts.model.Firestation;
import com.openclassrooms.safetyAlerts.model.Medicalrecord;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.exceptions.DataRepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class DataRepository {
    // Permets de mapper du json en objet java
    private static final ObjectMapper objectMapper = new ObjectMapper();
    // C'est pour le LOG4J
    private static final Logger logger = LogManager.getLogger(DataRepository.class);
    // Cet objet va representer le json en mémoire JAVA
    private static Database database;
    // Boolean pour valider le commit (main OK, test NOT OK)
    private boolean commit = true;
    private String DATA_JSON = "data.json";

    public Database getDatabase() {
        return this.database;
    }

    // Utilise les déclarations ci dessus pour mapper l'objet.
    public DataRepository() throws IOException {
        this.init();
    }

    public void init() {
        try (InputStream ips = ClassLoader.getSystemResourceAsStream(DATA_JSON)) {
            database = objectMapper.readerFor(Database.class).readValue(ips);
            logger.info("OK - File_OPEN : " + DATA_JSON);
        } catch (FileNotFoundException fnfe) {
            logger.info("KO - File_NOT_FOUND : " + DATA_JSON);
            throw new DataRepositoryException("KO - File_NOT_FOUND : ", fnfe);
        } catch (IOException ioe) {
            logger.info("KO - I/O ERROR : " + DATA_JSON);
            throw new DataRepositoryException("KO - I/O ERROR ", ioe);
        }
    }

    public void commit() {
        // récup le path du JSON
        URL url = ClassLoader.getSystemResource(DATA_JSON);
        // on ouvre un flux d'écriture vers le JSON
        try (OutputStream ops = new FileOutputStream(url.getFile())) {
            // ecrire le fichier JSON avec un formatage
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(ops, database);
            logger.info("OK - File_UPDATED : " + DATA_JSON);
        } catch (FileNotFoundException fnfe) {
            logger.info("KO - File_NOT_FOUND : " + DATA_JSON);
            throw new DataRepositoryException("KO - File_NOT_FOUND : ", fnfe);
        } catch (IOException ioe) {
            logger.info("KO - I/O ERROR : " + DATA_JSON);
            throw new DataRepositoryException("KO - I/O ERROR ", ioe);
        }
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }


    // méthode pour récup les Firestation par addresse en String
    public Collection<Firestation> getFirestation(String address) {
        Collection<Firestation> firestationCollection = new ArrayList<>();
        for (Firestation firestation : database.getFirestations()) {
            if (firestation.getAddress().equalsIgnoreCase(address)) {
                firestationCollection.add(firestation);
            }
        }
        return firestationCollection;
    }

    // méthode pour récup une Collection de MedicalRecord avec la date de naissance en String
    public Collection<Medicalrecord> getMedicalrecords(String birthdate) {
        Collection<Medicalrecord> medicalrecordCollection = new ArrayList<>();
        for (Medicalrecord medicalrecord : database.getMedicalrecords()) {
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
