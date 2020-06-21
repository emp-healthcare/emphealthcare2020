package org.emp.emphealth.repositories.resources;


import java.util.List;

import org.emp.emphealth.core.resources.Praticien;
import org.emp.emphealth.core.resources.PraticienTraitePatient;
import org.springframework.data.repository.CrudRepository;

public interface PraticienTraitePatientRepository extends CrudRepository<PraticienTraitePatient, String>{
	//List<PraticienTraitePatient> findBypraticien(Praticien praticien);
}