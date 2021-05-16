package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("fakeDao")
public class FakePersonDaoImpl implements PersonDao {
    private static List<Person> fakeDb = new ArrayList<>();

    @Override
    public int insertPerson(UUID uuid, Person person) {
        fakeDb.add(new Person(uuid, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPerson() {
        return fakeDb;
    }

    @Override
    public Optional<Person> getPerson(UUID id) {
        return fakeDb.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updatePerson(Person person) {
        Optional<Person> findPerson = getPerson(person.getId());
        if (findPerson.isEmpty()) {
            return 0;
        }
        int index = fakeDb.indexOf(findPerson.get());
        fakeDb.set(index, person);
        return 1;
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> person = getPerson(id);
        if (person.isEmpty()) {
            return 0;
        }
        fakeDb.remove(person.get());
        return 1;
    }
}
