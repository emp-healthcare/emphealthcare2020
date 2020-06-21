package org.emp.emphealth.services.dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.dataTypes.Mesure;
import org.emp.emphealth.repositories.dataTypes.MesureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesureService {
	@Autowired
	private MesureRepository MesureRepository;
	
	public List<Mesure> getAllMesure() {
		List<Mesure> admins = new ArrayList<Mesure>();
		MesureRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateMesure(Mesure dispositif, String id) {
		MesureRepository.save(dispositif);		
	}

	public Optional<Mesure> getMesure(String id) {

		return MesureRepository.findById(id);
	}

	public void addMesure(Mesure dispositif) {
		MesureRepository.save(dispositif);
		
	}

	public void deleteMesure(String id) {
		MesureRepository.deleteById(id);
	}
}