/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.emphealth.core.dataTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "mesure")
public class Mesure {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "type")
    private String type;
    
	@Column(name = "description")
    private String description;
	
	@Column(name = "unite")
    private String unite;

	public Mesure(String type, String description, String unite) {
		super();
		this.type = type;
		this.description = description;
		this.unite = unite;
	}

	public Mesure() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

    
    
    
}
