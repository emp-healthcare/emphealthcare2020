package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Patient;
import org.emp.emphealth.repositories.resources.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
	@Autowired
	private PatientRepository PatientRepository;
	
	public List<Patient> getAllPatient() {
		List<Patient> admins = new ArrayList<Patient>();
		PatientRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updatePatient(Patient dispositif, String id) {
		PatientRepository.save(dispositif);		
	}

	public Optional<Patient> getPatient(String id) {

		return PatientRepository.findById(id);
	}

	public void addPatient(Patient dispositif) {
		PatientRepository.save(dispositif);
		
	}

	public void deletePatient(String id) {
		PatientRepository.deleteById(id);
	}
}