package org.emp.emphealth.core.controllers;

import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Patient;
import org.emp.emphealth.services.resources.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@RequestMapping("/patient")
	public List<Patient> getAllPatients() {
		return patientService.getAllPatient();
	}
	
	@RequestMapping("/patient/{id}")
	public Optional<Patient> getPatient(@PathVariable String id) {
		return patientService.getPatient(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/patient")
	public String addPatient(@RequestBody Patient patient) {
		patientService.addPatient(patient);
		return "Ajout réussi avec succès";
	}
	
	/*@RequestMapping(method=RequestMethod.PUT, value="/patient/{id}")
	public void updatePatient(@RequestBody Patient patient, @PathVariable String id) {
		patientService.updatePatient(patient,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/patient/{id}")
	public void deletePatient(@PathVariable String id) {
		patientService.deletePatient(id);
	}*/
}