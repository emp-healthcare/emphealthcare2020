package org.emp.emphealth.core.controllers;

import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.services.resources.CapteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class CapteurController {

	@Autowired
	private CapteurService capteurService;
	
	@RequestMapping("/capteur")
	public List<Capteur> getAllCapteurs() {
		return capteurService.getAllCapteur();
	}
	
	@RequestMapping("/capteur/patient/{id}")
	public List<Capteur> getAllCapteursByPatient(@PathVariable String id) {
		return capteurService.getAllCapteurByPatient(id);
	}
	
	
	@RequestMapping("/capteur/{id}")
	public Optional<Capteur> getCapteur(@PathVariable String id) {
		return capteurService.getCapteur(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/capteur")
	public String addCapteur(@RequestBody Capteur capteur) {
		capteurService.addCapteur(capteur);
		return "Ajout réussi avec succès";
	}
	
	/*@RequestMapping(method=RequestMethod.PUT, value="/capteur/{id}")
	public void updateCapteur(@RequestBody Capteur capteur, @PathVariable String id) {
		capteurService.updateCapteur(capteur,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/capteur/{id}")
	public void deleteCapteur(@PathVariable String id) {
		capteurService.deleteCapteur(id);
	}*/
}