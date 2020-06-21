package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Praticien;
import org.emp.emphealth.repositories.resources.PraticienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraticienService {
	@Autowired
	private PraticienRepository PraticienRepository;
	
	public List<Praticien> getAllPraticien() {
		List<Praticien> admins = new ArrayList<Praticien>();
		PraticienRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updatePraticien(Praticien dispositif, String id) {
		PraticienRepository.save(dispositif);		
	}

	public Optional<Praticien> getPraticien(String id) {

		return PraticienRepository.findById(id);
	}

	public void addPraticien(Praticien dispositif) {
		PraticienRepository.save(dispositif);
		
	}

	public void deletePraticien(String id) {
		PraticienRepository.deleteById(id);
	}
}