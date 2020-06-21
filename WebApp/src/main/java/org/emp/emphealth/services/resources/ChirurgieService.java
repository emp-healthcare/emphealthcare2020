package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Chirurgie;
import org.emp.emphealth.repositories.resources.ChirurgieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChirurgieService {
	@Autowired
	private ChirurgieRepository ChirurgieRepository;
	
	public List<Chirurgie> getAllChirurgie() {
		List<Chirurgie> admins = new ArrayList<Chirurgie>();
		ChirurgieRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateChirurgie(Chirurgie dispositif, String id) {
		ChirurgieRepository.save(dispositif);		
	}

	public Optional<Chirurgie> getChirurgie(String id) {

		return ChirurgieRepository.findById(id);
	}

	public void addChirurgie(Chirurgie dispositif) {
		ChirurgieRepository.save(dispositif);
		
	}

	public void deleteChirurgie(String id) {
		ChirurgieRepository.deleteById(id);
	}
	
	
}