package org.emp.emphealth;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.emp.emphealth.core.dataTypes.Adresse;
import org.emp.emphealth.core.dataTypes.ParamsCLPKC;
import org.emp.emphealth.core.dataTypes.Photo;
import org.emp.emphealth.core.resources.Personne;
import org.emp.emphealth.core.resources.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails{
	
	private String IDPersonne;
    private String nom;
    private String prenom;
    private String genre;
    private Date dateNaissance;
    private String statutMatrimonial;
    private String numeroTelephone;
    private Adresse adresse;
    private String username;
    private String password;
    private Timestamp dateCreation;
    private Photo photo;
    private ParamsCLPKC params;
    private Role role;
	
	public MyUserDetails(Personne user) {
		super();
		IDPersonne = user.getIDPersonne();
		this.nom = user.getNom();
		this.prenom = user.getPrenom();
		this.genre = user.getGenre();
		this.dateNaissance = user.getDateNaissance();
		this.statutMatrimonial = user.getStatutMatrimonial();
		this.numeroTelephone = user.getNumeroTelephone();
		this.adresse = user.getAdresse();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.dateCreation = user.getDateCreation();
		this.photo = user.getPhoto();
		this.params = user.getParams();
		this.role = user.getRole();
		
	}



	public MyUserDetails() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new LinkedList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
		
		
		return authorities;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	


	public String getIDPersonne() {
		return IDPersonne;
	}



	public String getNom() {
		return nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public String getGenre() {
		return genre;
	}



	public Date getDateNaissance() {
		return dateNaissance;
	}



	public String getStatutMatrimonial() {
		return statutMatrimonial;
	}



	public String getNumeroTelephone() {
		return numeroTelephone;
	}



	public Adresse getAdresse() {
		return adresse;
	}



	public Timestamp getDateCreation() {
		return dateCreation;
	}



	public Photo getPhoto() {
		return photo;
	}



	public ParamsCLPKC getParams() {
		return params;
	}



	


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
