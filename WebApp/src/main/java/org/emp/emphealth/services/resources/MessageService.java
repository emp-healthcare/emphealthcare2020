package org.emp.emphealth.services.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.emp.emphealth.core.resources.Message;
import org.emp.emphealth.repositories.resources.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	private MessageRepository MessageRepository;
	
	public List<Message> getAllMessage() {
		List<Message> admins = new ArrayList<Message>();
		MessageRepository.findAll().forEach(admins::add);
		return admins;
	}

	public void updateMessage(Message dispositif, String id) {
		MessageRepository.save(dispositif);		
	}

	public Optional<Message> getMessage(String id) {

		return MessageRepository.findById(id);
	}

	public void addMessage(Message dispositif) {
		MessageRepository.save(dispositif);
		
	}

	public void deleteMessage(String id) {
		MessageRepository.deleteById(id);
	}
	
	
}