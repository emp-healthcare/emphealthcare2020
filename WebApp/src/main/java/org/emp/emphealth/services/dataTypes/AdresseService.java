package org.emp.emphealth.services.dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.dataTypes.Adresse;
import org.emp.emphealth.repositories.dataTypes.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdresseService {
	@Autowired
	private AdresseRepository AdresseRepository;
	
	public List<Adresse> getAllAdresse() {
		List<Adresse> admins = new ArrayList<Adresse>();
		AdresseRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateAdresse(Adresse dispositif, String id) {
		AdresseRepository.save(dispositif);		
	}

	public Optional<Adresse> getAdresse(String id) {

		return AdresseRepository.findById(id);
	}

	public void addAdresse(Adresse dispositif) {
		AdresseRepository.save(dispositif);
		
	}

	public void deleteAdresse(String id) {
		AdresseRepository.deleteById(id);
	}
}