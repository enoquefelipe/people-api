package com.people.service;

import com.people.model.Person;
import com.people.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository repository;

    /**
     *
     * @param id
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     *
     * @return
     */
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Person> findById(String id) {
        return repository.findById(id);
    }

    /**
     *
     * @param person
     */
    public Person save(Person person) {
        return repository.save(person);
    }

}
