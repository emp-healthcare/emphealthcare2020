package org.emp.emphealth.repositories.resources;



import java.util.List;

import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.core.resources.KeyStore;
import org.springframework.data.repository.CrudRepository;

public interface KeyStoreRepository extends CrudRepository<KeyStore, String>{

	List<KeyStore> findBycapteur(Capteur dispositif);
}