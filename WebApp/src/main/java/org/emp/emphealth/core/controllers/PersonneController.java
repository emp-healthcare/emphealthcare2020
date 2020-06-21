package org.emp.emphealth.core.controllers;

import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Personne;
import org.emp.emphealth.services.resources.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PersonneController {

	@Autowired
	private PersonneService personneService;
	
	@RequestMapping("/personne")
	public List<Personne> getAllPersonnes() {
		return personneService.getAllPersonne();
	}
	
	
	@RequestMapping("/personne/{username}")
	public Optional<Personne> getPersonneByUsername(@PathVariable String username) {
		return personneService.getPersonneByUsername(username);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/personne")
	public String addPersonne(@RequestBody Personne personne) {
		personneService.addPersonne(personne);
		return "Ajout réussi avec succès";
	}
	
	/*@RequestMapping(method=RequestMethod.PUT, value="/personne/{id}")
	public void updatePersonne(@RequestBody Personne personne, @PathVariable String id) {
		personneService.updatePersonne(personne,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/personne/{id}")
	public void deletePersonne(@PathVariable String id) {
		personneService.deletePersonne(id);
	}*/
}