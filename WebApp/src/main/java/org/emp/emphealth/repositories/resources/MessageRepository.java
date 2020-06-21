package org.emp.emphealth.repositories.resources;


import org.emp.emphealth.core.resources.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, String>{

}