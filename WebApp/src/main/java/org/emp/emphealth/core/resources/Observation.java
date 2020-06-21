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
@Table(name = "observation")
public class Observation {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_observation")
    private String IDObservation;
    
	@Column(name = "date_observation")
    private Timestamp dateObservation;
    
	@Column(name = "valeur")
    private String valeur;
    
	@ManyToOne
	@JoinColumn(name="id_capteur")
    private Capteur capteur;

    


    public Observation(String iDObservation, Timestamp dateObservation, String valeur, Capteur capteur) {
		super();
		IDObservation = iDObservation;
		this.dateObservation = dateObservation;
		this.valeur = valeur;
		this.capteur = capteur;
	}

	public Observation() {
    }

    public String getIDObservation() {
        return IDObservation;
    }

    public void setIDObservation(String IDObservation) {
        this.IDObservation = IDObservation;
    }

    public Timestamp getDateObservation() {
        return dateObservation;
    }

    public void setDateObservation(Timestamp dateObservation) {
        this.dateObservation = dateObservation;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Capteur getCapteur() {
        return capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
    
    
}
