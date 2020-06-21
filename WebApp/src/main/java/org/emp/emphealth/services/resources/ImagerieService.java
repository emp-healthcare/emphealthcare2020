package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Imagerie;
import org.emp.emphealth.repositories.resources.ImagerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagerieService {
	@Autowired
	private ImagerieRepository ImagerieRepository;
	
	public List<Imagerie> getAllImagerie() {
		List<Imagerie> admins = new ArrayList<Imagerie>();
		ImagerieRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateImagerie(Imagerie dispositif, String id) {
		ImagerieRepository.save(dispositif);		
	}

	public Optional<Imagerie> getImagerie(String id) {

		return ImagerieRepository.findById(id);
	}

	public void addImagerie(Imagerie dispositif) {
		ImagerieRepository.save(dispositif);
		
	}

	public void deleteImagerie(String id) {
		ImagerieRepository.deleteById(id);
	}
	
	
}