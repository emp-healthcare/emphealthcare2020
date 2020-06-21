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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.emp.emphealth.core.dataTypes.Mesure;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "capteur")
public class Capteur {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_capteur")
    private String IDCapteur;
    
	@Column(name = "fabricant")
    private String fabricant;
    
	@Column(name = "nom")
    private String nom;
    
	@Column(name = "modele")
    private String modele;
    
	@ManyToOne
	@JoinColumn(name="type")
    private Mesure mesure;
    
	@JsonIgnore
	@OneToMany(mappedBy="capteur")
    private List<KeyStore> cles;
    
    @JsonIgnore
	@OneToMany(mappedBy="capteur")
    private List<Observation> observations;
    
    @ManyToOne
	@JoinColumn(name="id_personne")
    private Patient patient;

    @ManyToOne
	@JoinColumn(name="demandeur")
    private Praticien praticien;

	public Capteur(String iDCapteur, String fabricant, String nom, String modele, Mesure mesure, List<KeyStore> cles,
			List<Observation> observations, Patient patient, Praticien praticien) {
		super();
		IDCapteur = iDCapteur;
		this.fabricant = fabricant;
		this.nom = nom;
		this.modele = modele;
		this.mesure = mesure;
		this.cles = cles;
		this.observations = observations;
		this.patient = patient;
		this.praticien = praticien;
	}

	public Capteur() {
		super();
	}

	public String getIDCapteur() {
		return IDCapteur;
	}

	public void setIDCapteur(String iDCapteur) {
		IDCapteur = iDCapteur;
	}

	public String getFabricant() {
		return fabricant;
	}

	public void setFabricant(String fabricant) {
		this.fabricant = fabricant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Mesure getMesure() {
		return mesure;
	}

	public void setMesure(Mesure mesure) {
		this.mesure = mesure;
	}

	public List<KeyStore> getCles() {
		return cles;
	}

	public void setCles(List<KeyStore> cles) {
		this.cles = cles;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
	}
    
    
    
}
