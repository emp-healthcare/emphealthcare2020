package org.emp.emphealth.repositories.resources;



import java.util.List;

import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.core.resources.Patient;
import org.emp.emphealth.core.resources.Praticien;
import org.springframework.data.repository.CrudRepository;

public interface CapteurRepository extends CrudRepository<Capteur, String>{
	List<Capteur> findBypraticien(Praticien praticien);
	List<Capteur> findBypatient(Patient patient);
}