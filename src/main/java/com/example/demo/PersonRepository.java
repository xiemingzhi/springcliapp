package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {

    // additional custom finder methods go here
}