package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Examen;
import org.emp.emphealth.repositories.resources.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamenService {
	@Autowired
	private ExamenRepository ExamenRepository;
	
	public List<Examen> getAllExamen() {
		List<Examen> admins = new ArrayList<Examen>();
		ExamenRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateExamen(Examen dispositif, String id) {
		ExamenRepository.save(dispositif);		
	}

	public Optional<Examen> getExamen(String id) {

		return ExamenRepository.findById(id);
	}

	public void addExamen(Examen dispositif) {
		ExamenRepository.save(dispositif);
		
	}

	public void deleteExamen(String id) {
		ExamenRepository.deleteById(id);
	}
	
	
}