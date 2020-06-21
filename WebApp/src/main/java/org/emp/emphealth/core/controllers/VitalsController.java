package org.emp.emphealth.core.controllers;

import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Vitals;
import org.emp.emphealth.services.resources.VitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class VitalsController {

	@Autowired
	private VitalsService vitalsService;
	
	@RequestMapping("/vitals")
	public List<Vitals> getAllVitalss() {
		return vitalsService.getAllVitals();
	}
	
	@RequestMapping("/vitals/{id}")
	public Optional<Vitals> getVitals(@PathVariable String id) {
		return vitalsService.getVitals(id);
	}
	
	@RequestMapping("/vitals/patient/{id}")
	public List<Vitals> getVitalsByUsername(@PathVariable String id) {
		return vitalsService.getAllVitalsByUsername(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/vitals")
	public String addVitals(@RequestBody Vitals vitals) {
		vitalsService.addVitals(vitals);
		return "Ajout réussi avec succès";
	}
	
	
	/*@RequestMapping(method=RequestMethod.PUT, value="/vitals/{id}")
	public void updateVitals(@RequestBody Vitals vitals, @PathVariable String id) {
		vitalsService.updateVitals(vitals,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/vitals/{id}")
	public void deleteVitals(@PathVariable String id) {
		vitalsService.deleteVitals(id);
	}*/
}