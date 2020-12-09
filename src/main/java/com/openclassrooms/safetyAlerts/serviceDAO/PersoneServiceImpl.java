package com.openclassrooms.safetyAlerts.serviceDAO;


import com.openclassrooms.safetyAlerts.dao.IPersonDAO;
import com.openclassrooms.safetyAlerts.dao.PersonDAO;
import com.openclassrooms.safetyAlerts.exceptions.DataAlreadyExistException;
import com.openclassrooms.safetyAlerts.exceptions.DataNotFoundException;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersoneServiceImpl implements IPersonService {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    IPersonDAO personDAO;

    @Override
    public boolean createPerson(Person person) {
        // verification si la personne existe déjà dans le data json
        if (!dataRepository.getDatabase().getPersons().contains(person)) {
            personDAO.createPerson(person);
            return true;
        }
        else {
            throw new DataAlreadyExistException("La personne " + person.getFirstName() + " " + person.getLastName() + " existe déjà. ");
        }
    }

    @Override
    public boolean deletePerson(Person person) {
        // verif que la personne existe
        if (!personDAO.deletePerson(person)) {
            throw new DataNotFoundException("La personne " + person.getFirstName() + " " + person.getLastName() + " n'existe pas. ");
        }
        return true;
    }

    @Override
    public boolean updatePerson(Person person) {
        // verif que la personne existe
        if (!personDAO.updatePerson(person)) {
            throw new DataNotFoundException("La personne " + person.getFirstName() + " " + person.getLastName() + " n'existe pas. ");
        }
        return true;
    }
}
