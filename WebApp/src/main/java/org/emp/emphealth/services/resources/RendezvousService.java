package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Rendezvous;
import org.emp.emphealth.repositories.resources.RendezvousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RendezvousService {
	@Autowired
	private RendezvousRepository RendezvousRepository;
	
	public List<Rendezvous> getAllRendezvous() {
		List<Rendezvous> admins = new ArrayList<Rendezvous>();
		RendezvousRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateRendezvous(Rendezvous dispositif, String id) {
		RendezvousRepository.save(dispositif);		
	}

	public Optional<Rendezvous> getRendezvous(String id) {

		return RendezvousRepository.findById(id);
	}

	public void addRendezvous(Rendezvous dispositif) {
		RendezvousRepository.save(dispositif);
		
	}

	public void deleteRendezvous(String id) {
		RendezvousRepository.deleteById(id);
	}
}