package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Role;
import org.emp.emphealth.repositories.resources.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	@Autowired
	private RoleRepository RoleRepository;
	
	public List<Role> getAllRole() {
		List<Role> admins = new ArrayList<Role>();
		RoleRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateRole(Role dispositif, String id) {
		RoleRepository.save(dispositif);		
	}

	public Optional<Role> getRole(String id) {

		return RoleRepository.findById(id);
	}

	public void addRole(Role dispositif) {
		RoleRepository.save(dispositif);
		
	}

	public void deleteRole(String id) {
		RoleRepository.deleteById(id);
	}
}