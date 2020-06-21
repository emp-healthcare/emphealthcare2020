package org.emp.emphealth.services.dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.dataTypes.Allergene;
import org.emp.emphealth.repositories.dataTypes.AllergeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergeneService {
	@Autowired
	private AllergeneRepository AllergeneRepository;
	
	public List<Allergene> getAllAllergene() {
		List<Allergene> admins = new ArrayList<Allergene>();
		AllergeneRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateAllergene(Allergene dispositif, String id) {
		AllergeneRepository.save(dispositif);		
	}

	public Optional<Allergene> getAllergene(String id) {

		return AllergeneRepository.findById(id);
	}

	public void addAllergene(Allergene dispositif) {
		AllergeneRepository.save(dispositif);
		
	}

	public void deleteAllergene(String id) {
		AllergeneRepository.deleteById(id);
	}
}