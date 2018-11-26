package com.people.controller;

import com.people.model.Person;
import com.people.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    private PeopleService service;

    @GetMapping("/people")
    public @ResponseBody List<Person> getAll(){
        return (List<Person>) service.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Person person, UriComponentsBuilder uriBuilder){
        HttpHeaders headers = createURI(uriBuilder, service.save(person));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "person/{id}")
    public Optional<Person> getPerson(@PathVariable String id) {
        return service.findById(id);
    }

    private HttpHeaders createURI(UriComponentsBuilder uriBuilder, Person person) {
        URI location = uriBuilder.path("/api/person/{id}").buildAndExpand(person.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return headers;
    }

}
