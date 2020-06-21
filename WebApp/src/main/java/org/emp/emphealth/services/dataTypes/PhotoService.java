package org.emp.emphealth.services.dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.dataTypes.Photo;
import org.emp.emphealth.repositories.dataTypes.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepository PhotoRepository;
	
	public List<Photo> getAllPhoto() {
		List<Photo> admins = new ArrayList<Photo>();
		PhotoRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updatePhoto(Photo dispositif, String id) {
		PhotoRepository.save(dispositif);		
	}

	public Optional<Photo> getPhoto(String id) {

		return PhotoRepository.findById(id);
	}

	public void addPhoto(Photo dispositif) {
		PhotoRepository.save(dispositif);
		
	}

	public void deletePhoto(String id) {
		PhotoRepository.deleteById(id);
	}
}