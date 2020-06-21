package org.emp.emphealth.repositories.resources;


import java.util.List;

import org.emp.emphealth.core.resources.Vitals;
import org.springframework.data.repository.CrudRepository;

public interface VitalsRepository extends CrudRepository<Vitals, String>{
	List<Vitals> findByPatientIDPersonne(String id);

}