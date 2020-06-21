package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Prescription;
import org.emp.emphealth.repositories.resources.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
	@Autowired
	private PrescriptionRepository PrescriptionRepository;
	
	public List<Prescription> getAllPrescription() {
		List<Prescription> admins = new ArrayList<Prescription>();
		PrescriptionRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updatePrescription(Prescription dispositif, String id) {
		PrescriptionRepository.save(dispositif);		
	}

	public Optional<Prescription> getPrescription(String id) {

		return PrescriptionRepository.findById(id);
	}

	public void addPrescription(Prescription dispositif) {
		PrescriptionRepository.save(dispositif);
		
	}

	public void deletePrescription(String id) {
		PrescriptionRepository.deleteById(id);
	}
	
	
}