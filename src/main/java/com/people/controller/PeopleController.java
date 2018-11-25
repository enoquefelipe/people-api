package com.people.controller;

import com.people.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PeopleController {

    @GetMapping("/status")
    public String index(){
        return "UP";
    }

    @GetMapping("/people")
    public @ResponseBody List<Person> findAll(){
        return Arrays.asList(new Person("Enoque Leal", "634.861.628-29"));
    }
}
