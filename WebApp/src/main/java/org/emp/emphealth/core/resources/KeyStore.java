/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.emphealth.core.resources;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author ambara
 */
@Entity
@Table(name = "keystore")
public class KeyStore implements Comparable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_keystore")
    private String IDKeystore;
    
	@Column(name = "cle")
    private String cle;
    
	@Column(name = "date_creation")
    private Timestamp dateCreation;
    
	@Column(name = "date_revocation")
    private Timestamp dateRevocation;
    
	@ManyToOne
	@JoinColumn(name="id_capteur")
    private Capteur capteur;

    public KeyStore(String IDKeystore, String cle, Timestamp dateCreation, Timestamp dateRevocation, Capteur capteur) {
        this.IDKeystore = IDKeystore;
        this.cle = cle;
        this.dateCreation = dateCreation;
        this.dateRevocation = dateRevocation;
        this.capteur = capteur;
    }
    
    

    public KeyStore() {
		super();
	}



	public String getIDKeystore() {
        return IDKeystore;
    }

    public void setIDKeystore(String IDKeystore) {
        this.IDKeystore = IDKeystore;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Timestamp getDateRevocation() {
        return dateRevocation;
    }

    public void setDateRevocation(Timestamp dateRevocation) {
        this.dateRevocation = dateRevocation;
    }

    public Capteur getCapteur() {
        return capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
    
    @Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return Integer.parseInt(this.IDKeystore) - Integer.parseInt(((KeyStore) arg0).getIDKeystore());
	}
}
