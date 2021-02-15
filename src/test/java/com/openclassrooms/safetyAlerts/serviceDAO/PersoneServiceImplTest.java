package com.openclassrooms.safetyAlerts.serviceDAO;

import com.openclassrooms.safetyAlerts.dao.PersonDAO;
import com.openclassrooms.safetyAlerts.model.Person;
import com.openclassrooms.safetyAlerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PersoneServiceImplTest {


    Person personSebastien = new Person("Patrick", "Sebastien", "555 chemin des fleurs", "Passaville", "78888", "555-555-555", "mail@mail");

    @Autowired
    PersonDAO personServiceDAO;

    @Autowired
    DataRepository dataRepository;

    @BeforeEach
    void init() {
        dataRepository.init();
        dataRepository.setCommit(false);
    }



    @Test
    void createPerson() {
        assertThat(dataRepository.getAllPersons().contains(personSebastien)).isFalse();
        assertThat(personServiceDAO.createPerson(personSebastien)).isTrue();
        assertThat(dataRepository.getAllPersons().contains(personSebastien)).isTrue();
    }

    @Test
    void deletePerson() {
        assertThat(personServiceDAO.deletePerson(personSebastien)).isFalse();
        assertThat(personServiceDAO.createPerson(personSebastien)).isTrue();
        assertThat(dataRepository.getAllPersons().contains(personSebastien)).isTrue();
        assertThat(personServiceDAO.deletePerson(personSebastien)).isTrue();
        assertThat(dataRepository.getAllPersons().contains(personSebastien)).isFalse();
    }

    @Test
    void updatePerson() {
        assertThat(personServiceDAO.updatePerson(personSebastien)).isFalse();
        assertThat(personServiceDAO.createPerson(personSebastien)).isTrue();
        assertThat(dataRepository.getAllPersons().contains(personSebastien)).isTrue();
        assertThat(personServiceDAO.updatePerson(personSebastien)).isTrue();
    }
}