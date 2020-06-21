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
@Table(name = "adressepersonne")
public class Adresse {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_adresse")
    private int IDAdresse;
    
	@Column(name = "adresse")
    private String adresse;
    
	@Column(name = "ville")
    private String ville;
    
	@Column(name = "region")
    private String region;
    
	@Column(name = "bp")
    private String BP;

    public Adresse(int IDAdresse, String adresse, String ville, String region, String BP) {
        this.IDAdresse = IDAdresse;
        this.adresse = adresse;
        this.ville = ville;
        this.region = region;
        this.BP = BP;
    }

    public Adresse() {
    }

    public int getIDAdresse() {
        return IDAdresse;
    }

    public void setIDAdresse(int IDAdresse) {
        this.IDAdresse = IDAdresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBP() {
        return BP;
    }

    public void setBP(String BP) {
        this.BP = BP;
    }
    
    
}
