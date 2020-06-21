package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Personne;
import org.emp.emphealth.repositories.resources.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonneService {
	@Autowired
	private PersonneRepository PersonneRepository;
	
	public List<Personne> getAllPersonne() {
		List<Personne> admins = new ArrayList<Personne>();
		PersonneRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updatePersonne(Personne dispositif, String id) {
		PersonneRepository.save(dispositif);		
	}

	public Optional<Personne> getPersonne(String id) {

		return PersonneRepository.findById(id);
	}

	public void addPersonne(Personne dispositif) {
		PersonneRepository.save(dispositif);
		
	}

	public void deletePersonne(String id) {
		PersonneRepository.deleteById(id);
	}
	
	public Optional<Personne> getPersonneByUsername(String username) {

		return PersonneRepository.findByusername(username);
	}
}