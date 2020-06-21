package org.emp.emphealth.repositories.dataTypes;


import org.emp.emphealth.core.dataTypes.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, String>{
}