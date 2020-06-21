package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Dentspb;
import org.emp.emphealth.repositories.resources.DentspbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentspbService {
	@Autowired
	private DentspbRepository DentspbRepository;
	
	public List<Dentspb> getAllDentspb() {
		List<Dentspb> admins = new ArrayList<Dentspb>();
		DentspbRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateDentspb(Dentspb dispositif, String id) {
		DentspbRepository.save(dispositif);		
	}

	public Optional<Dentspb> getDentspb(String id) {

		return DentspbRepository.findById(id);
	}

	public void addDentspb(Dentspb dispositif) {
		DentspbRepository.save(dispositif);
		
	}

	public void deleteDentspb(String id) {
		DentspbRepository.deleteById(id);
	}
	
	
}