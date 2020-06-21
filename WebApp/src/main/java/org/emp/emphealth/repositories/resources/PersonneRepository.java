package org.emp.emphealth.repositories.resources;



import java.util.Optional;

import org.emp.emphealth.core.resources.Personne;
import org.springframework.data.repository.CrudRepository;

public interface PersonneRepository extends CrudRepository<Personne, String>{
	Optional<Personne> findByusername(String username);
}