package org.emp.emphealth.services.dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.dataTypes.ParamsCLPKC;
import org.emp.emphealth.repositories.dataTypes.ParamsCLPKCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamsCLPKCService {
	@Autowired
	private ParamsCLPKCRepository ParamsCLPKCRepository;
	
	public List<ParamsCLPKC> getAllParamsCLPKC() {
		List<ParamsCLPKC> admins = new ArrayList<ParamsCLPKC>();
		ParamsCLPKCRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateParamsCLPKC(ParamsCLPKC dispositif, String id) {
		ParamsCLPKCRepository.save(dispositif);		
	}

	public Optional<ParamsCLPKC> getParamsCLPKC(String id) {

		return ParamsCLPKCRepository.findById(id);
	}

	public void addParamsCLPKC(ParamsCLPKC dispositif) {
		ParamsCLPKCRepository.save(dispositif);
		
	}

	public void deleteParamsCLPKC(String id) {
		ParamsCLPKCRepository.deleteById(id);
	}
}