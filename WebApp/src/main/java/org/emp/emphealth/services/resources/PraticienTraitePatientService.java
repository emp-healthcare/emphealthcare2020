package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Praticien;
import org.emp.emphealth.core.resources.PraticienTraitePatient;
import org.emp.emphealth.repositories.resources.PraticienTraitePatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraticienTraitePatientService {
	@Autowired
	private PraticienTraitePatientRepository PraticienTraitePatientRepository;
	
	public List<PraticienTraitePatient> getAllPraticienTraitePatient() {
		List<PraticienTraitePatient> admins = new ArrayList<PraticienTraitePatient>();
		PraticienTraitePatientRepository.findAll().forEach(admins::add);
		return admins;
	}
	
	/*public List<PraticienTraitePatient> getAllPraticienTraitePatientByPraticien(Praticien praticien) {
		List<PraticienTraitePatient> admins = new ArrayList<PraticienTraitePatient>();
		PraticienTraitePatientRepository.findBypraticien(praticien).forEach(admins::add);
		return admins;
	}
*/
	public void updatePraticienTraitePatient(PraticienTraitePatient dispositif, String id) {
		PraticienTraitePatientRepository.save(dispositif);		
	}

	public Optional<PraticienTraitePatient> getPraticienTraitePatient(String id) {

		return PraticienTraitePatientRepository.findById(id);
	}

	public void addPraticienTraitePatient(PraticienTraitePatient dispositif) {
		PraticienTraitePatientRepository.save(dispositif);
		
	}

	public void deletePraticienTraitePatient(String id) {
		PraticienTraitePatientRepository.deleteById(id);
	}
}