package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Allergie;
import org.emp.emphealth.repositories.resources.AllergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergieService {
	@Autowired
	private AllergieRepository AllergieRepository;
	
	public List<Allergie> getAllAllergie() {
		List<Allergie> admins = new ArrayList<Allergie>();
		AllergieRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateAllergie(Allergie dispositif, String id) {
		AllergieRepository.save(dispositif);		
	}

	public Optional<Allergie> getAllergie(String id) {

		return AllergieRepository.findById(id);
	}

	public void addAllergie(Allergie dispositif) {
		AllergieRepository.save(dispositif);
		
	}

	public void deleteAllergie(String id) {
		AllergieRepository.deleteById(id);
	}
	
	
}