package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Observation;
import org.emp.emphealth.repositories.resources.CapteurRepository;
import org.emp.emphealth.repositories.resources.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationService {
	@Autowired
	private ObservationRepository ObservationRepository;
	
	@Autowired
	private CapteurRepository capteurRepository;
	
	public List<Observation> getAllObservation() {
		List<Observation> admins = new ArrayList<Observation>();
		ObservationRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateObservation(Observation dispositif, String id) {
		ObservationRepository.save(dispositif);		
	}

	public Optional<Observation> getObservation(String id) {

		return ObservationRepository.findById(id);
	}

	public void addObservation(Observation dispositif) {
		ObservationRepository.save(dispositif);
		
	}

	public void deleteObservation(String id) {
		ObservationRepository.deleteById(id);
	}
	
	public List<Observation> getAllObservationByCapteur(String idcapteur) {
		List<Observation> admins = new ArrayList<Observation>();
		ObservationRepository.findByCapteur(capteurRepository.findById(idcapteur).get()).forEach(admins::add);
		return admins;
	}
}