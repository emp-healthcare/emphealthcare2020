package org.emp.emphealth;

import java.util.Optional;

import org.emp.emphealth.core.resources.Personne;
import org.emp.emphealth.repositories.resources.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	PersonneRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Personne> user = userRepository.findByusername(email);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Non trouvé: " + email));
		
		return user.map(MyUserDetails::new).get();
	}

}
