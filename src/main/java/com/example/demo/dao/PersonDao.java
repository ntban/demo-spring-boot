package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID uuid, Person person);
    default int insertPerson(Person person){
        UUID uuid = UUID.randomUUID();
        return insertPerson(uuid, person);
    }
    List<Person> getAllPerson();
    Optional<Person> getPerson(UUID id);
    int updatePerson(Person person);
    int deletePerson(UUID id);
}
