package org.emp.emphealth.core.controllers;

import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Observation;
import org.emp.emphealth.services.resources.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ObservationController {

	@Autowired
	private ObservationService observationService;
	
	@RequestMapping("/observation")
	public List<Observation> getAllObservations() {
		return observationService.getAllObservation();
	}
	
	@RequestMapping("/observation/capteur/{id}")
	public List<Observation> getAllObservationsByCapteur(@PathVariable String id) {
		return observationService.getAllObservationByCapteur(id);
	}
	
	@RequestMapping("/observation/{id}")
	public Optional<Observation> getObservation(@PathVariable String id) {
		return observationService.getObservation(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/observation")
	public String addObservation(@RequestBody Observation observation) {
		observationService.addObservation(observation);
		return "Ajout réussi avec succès";
	}
	
	/*@RequestMapping(method=RequestMethod.PUT, value="/observation/{id}")
	public void updateObservation(@RequestBody Observation observation, @PathVariable String id) {
		observationService.updateObservation(observation,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/observation/{id}")
	public void deleteObservation(@PathVariable String id) {
		observationService.deleteObservation(id);
	}*/
}