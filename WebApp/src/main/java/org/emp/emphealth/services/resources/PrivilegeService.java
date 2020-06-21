package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Privilege;
import org.emp.emphealth.repositories.resources.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
	@Autowired
	private PrivilegeRepository PrivilegeRepository;
	
	public List<Privilege> getAllPrivilege() {
		List<Privilege> admins = new ArrayList<Privilege>();
		PrivilegeRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updatePrivilege(Privilege dispositif, String id) {
		PrivilegeRepository.save(dispositif);		
	}

	public Optional<Privilege> getPrivilege(String id) {

		return PrivilegeRepository.findById(id);
	}

	public void addPrivilege(Privilege dispositif) {
		PrivilegeRepository.save(dispositif);
		
	}

	public void deletePrivilege(String id) {
		PrivilegeRepository.deleteById(id);
	}
}