package com.openclassrooms.safetyAlerts.dao;


import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDAO implements IPersonDAO {

    @Autowired
    DataRepository dataRepository;

    @Override
    public boolean createPerson(Person person) {
        // ajout de la nouvelle personne en mémoire (Object JAVA)
        dataRepository.getDatabase().getPersons().add(person);
        // commit pour appliquer les changements sur le json
        dataRepository.commit();

        return true;
    }

    @Override
    public boolean deletePerson(Person person) {
        // suppression de la personne en mémoire -(Object JAVA)
        boolean result=dataRepository.getDatabase().getPersons().remove(person);
        // commit
        dataRepository.commit();

        return result;
    }

    @Override
    public boolean updatePerson(Person person) {
        //
        if (dataRepository.getDatabase().getPersons().remove(person)) {
            createPerson(person);
            return true;
        }
        return false;
    }
}
