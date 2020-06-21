package org.emp.emphealth.repositories.resources;



import org.emp.emphealth.core.resources.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, String>{
	}