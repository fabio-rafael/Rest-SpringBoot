package com.api.PersonsCRUD.services;

import com.api.PersonsCRUD.exceptions.ResourceNotFoundException;
import com.api.PersonsCRUD.model.Person;
import com.api.PersonsCRUD.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    public List<Person> findAll(){
        logger.info("Finding All! ");
        List<Person> persons = new ArrayList<>();

        return repository.findAll();
    }



    public Person findById(Long id){
        logger.info("Finding One Person! ");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para este ID!"));
    }



    public Person create(Person person) {

        logger.info("Creating one person!");
        return repository.save(person);

    }

    public Person update(Person person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para este ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return repository.save(person);

    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para este ID!"));

        repository.delete(entity);

    }


}
