package org.emp.emphealth.repositories.resources;



import java.util.List;

import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.core.resources.Observation;
import org.springframework.data.repository.CrudRepository;

public interface ObservationRepository extends CrudRepository<Observation, String>{
	List<Observation> findByCapteur(Capteur capteur);
}