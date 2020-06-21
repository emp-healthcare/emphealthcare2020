package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.core.resources.KeyStore;
import org.emp.emphealth.repositories.resources.KeyStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyStoreService {
	@Autowired
	private KeyStoreRepository KeyStoreRepository;
	
	public List<KeyStore> getAllKeyStore() {
		List<KeyStore> admins = new ArrayList<KeyStore>();
		KeyStoreRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateKeyStore(KeyStore dispositif, String id) {
		KeyStoreRepository.save(dispositif);		
	}

	public Optional<KeyStore> getKeyStore(String id) {

		return KeyStoreRepository.findById(id);
	}

	public void addKeyStore(KeyStore dispositif) {
		KeyStoreRepository.save(dispositif);
		
	}

	public void deleteKeyStore(String id) {
		KeyStoreRepository.deleteById(id);
	}
	
	public List<KeyStore> getListkeys(Capteur dispositif) {
		return KeyStoreRepository.findBycapteur(dispositif);
	}
}