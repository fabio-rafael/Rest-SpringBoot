package com.api.PersonsCRUD.repositories;

import com.api.PersonsCRUD.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long > {
}
