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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "privilege")
public class Privilege {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "privilege")
    private String privilege;
    
	@Column(name = "description")
    private String description;
    
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "roleprivilege", joinColumns = @JoinColumn(name = "role"), inverseJoinColumns = @JoinColumn(name = "privilege"))
    private List<Role> privileges;

    public Privilege(String privilege, String description, List<Role> privileges) {
        this.privilege = privilege;
        this.description = description;
        this.privileges = privileges;
    }

    public Privilege() {
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Role> privileges) {
        this.privileges = privileges;
    }
    
    
}
