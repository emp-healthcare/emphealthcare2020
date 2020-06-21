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
@Table(name = "metier")
public class Metier {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "metier")
    private String metier;
    
	@Column(name = "description")
    private String description;

    public Metier(String metier, String description) {
        this.metier = metier;
        this.description = description;
    }

    public Metier() {
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
