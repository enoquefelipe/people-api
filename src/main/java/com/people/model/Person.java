package com.people.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    private Double id;
    private String name;
    private String document;

    public Person(String name, String document) {
        this.name = name;
        this.document = document;
    }

    /*
    Getters and Setters
    */

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
