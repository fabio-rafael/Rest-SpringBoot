package com.api.PersonsCRUD.services;


import com.api.PersonsCRUD.controller.PersonController;
import com.api.PersonsCRUD.data.vo.v1.PersonVO;
import com.api.PersonsCRUD.data.vo.v2.PersonVOV2;
import com.api.PersonsCRUD.exceptions.ResourceNotFoundException;
import com.api.PersonsCRUD.mapper.DozerMapper;
import com.api.PersonsCRUD.mapper.custom.PersonMapper;
import com.api.PersonsCRUD.model.Person;
import com.api.PersonsCRUD.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;
    public List<PersonVO> findAll(){
        logger.info("Finding All! ");
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }



    public PersonVO findById(Long id){
        logger.info("Finding One Person! ");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para este ID!"));
        var vo =  DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }



    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(person , Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;


    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person with V2!");
        var entity = mapper.convertVOTOEntity(person );
        var vo = mapper.convertEntityToVo(repository.save(entity));
        return vo;


    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para este ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para este ID!"));

        repository.delete(entity);

    }


}
