package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("postgresDao")
public class PostgresPersonDaoImpl implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresPersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID uuid, Person person) {
        final String sql = "INSERT INTO person(id, name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, new Object[]{uuid, person.getName()});
    }

    @Override
    public List<Person> getAllPerson() {
        final String sql = "SELECT * FROM person" ;
        List<Person> people = jdbcTemplate.query(sql, (resultSet , i) -> {
            return new Person(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name")
            );
        });
        return people;
    }

    @Override
    public Optional<Person> getPerson(UUID id) {
        final String sql = "SELECT * FROM person WHERE id = ?" ;
        Person person = jdbcTemplate.queryForObject(sql, (resultSet , i) -> {
            return new Person(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name")
            );
        }, new Object[]{id});
        return Optional.ofNullable(person);
    }

    @Override
    public int updatePerson(Person person) {
        final String sql = "UPDATE person SET name = ? WHERE id = ? ";
        return jdbcTemplate.update(sql, new Object[]{person.getName(), person.getId()});
    }

    @Override
    public int deletePerson(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ? ";
        return jdbcTemplate.update(sql, new Object[]{id});
    }
}
