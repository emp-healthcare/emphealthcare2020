/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.emphealth.core.resources;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "roles")
public class Role {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "role")
    private String role;
    
	@Column(name = "description")
    private String description;
    
	@JsonIgnore
	@OneToMany(mappedBy="role")
    private List<Personne> personnes;
    
	@JsonIgnore
	@ManyToMany(mappedBy = "privileges")
    private List<Privilege> privileges;

    

    public Role(String role, String description, List<Personne> personnes, List<Privilege> privileges) {
		super();
		this.role = role;
		this.description = description;
		this.personnes = personnes;
		this.privileges = privileges;
	}

	public Role() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

    
    
    
}
