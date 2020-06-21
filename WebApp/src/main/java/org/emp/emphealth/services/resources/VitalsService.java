package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Vitals;
import org.emp.emphealth.repositories.resources.VitalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VitalsService {
	@Autowired
	private VitalsRepository VitalsRepository;
	
	public List<Vitals> getAllVitals() {
		List<Vitals> admins = new ArrayList<Vitals>();
		VitalsRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateVitals(Vitals dispositif, String id) {
		VitalsRepository.save(dispositif);		
	}

	public Optional<Vitals> getVitals(String id) {

		return VitalsRepository.findById(id);
	}

	public void addVitals(Vitals dispositif) {
		VitalsRepository.save(dispositif);
		
	}

	public void deleteVitals(String id) {
		VitalsRepository.deleteById(id);
	}
	
	public List<Vitals> getAllVitalsByUsername(String username) {
		List<Vitals> admins = new ArrayList<Vitals>();
		admins = VitalsRepository.findByPatientIDPersonne(username);
		return admins;
	}
}