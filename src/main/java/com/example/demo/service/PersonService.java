package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    @Qualifier("postgresDao")
    private PersonDao personDao;

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPerson() {
        return personDao.getAllPerson();
    }

    public Person getPerson(UUID id) {
        return personDao.getPerson(id).orElse(null);
    }

    public int updatePerson(Person person) {
        return personDao.updatePerson(person);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePerson(id);
    }
}
