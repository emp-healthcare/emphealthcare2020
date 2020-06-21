package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.repositories.resources.CapteurRepository;
import org.emp.emphealth.repositories.resources.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapteurService {
	@Autowired
	private CapteurRepository CapteurRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public List<Capteur> getAllCapteur() {
		List<Capteur> admins = new ArrayList<Capteur>();
		CapteurRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateCapteur(Capteur dispositif, String id) {
		CapteurRepository.save(dispositif);		
	}

	public Optional<Capteur> getCapteur(String id) {

		return CapteurRepository.findById(id);
	}

	public void addCapteur(Capteur dispositif) {
		CapteurRepository.save(dispositif);
		
	}

	public void deleteCapteur(String id) {
		CapteurRepository.deleteById(id);
	}
	
	public List<Capteur> getAllCapteurByPatient(String id) {
		List<Capteur> admins = new ArrayList<Capteur>();
		CapteurRepository.findBypatient(patientRepository.findById(id).get()).forEach(admins::add);
		return admins;
	}
}