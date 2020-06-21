package org.emp.emphealth.services.dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.dataTypes.Metier;
import org.emp.emphealth.repositories.dataTypes.MetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetierService {
	@Autowired
	private MetierRepository MetierRepository;
	
	public List<Metier> getAllMetier() {
		List<Metier> admins = new ArrayList<Metier>();
		MetierRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateMetier(Metier dispositif, String id) {
		MetierRepository.save(dispositif);		
	}

	public Optional<Metier> getMetier(String id) {

		return MetierRepository.findById(id);
	}

	public void addMetier(Metier dispositif) {
		MetierRepository.save(dispositif);
		
	}

	public void deleteMetier(String id) {
		MetierRepository.deleteById(id);
	}
}