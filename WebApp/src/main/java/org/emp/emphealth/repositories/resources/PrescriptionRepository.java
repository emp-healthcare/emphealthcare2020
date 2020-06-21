package org.emp.emphealth.repositories.resources;


import org.emp.emphealth.core.resources.Prescription;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, String>{

}