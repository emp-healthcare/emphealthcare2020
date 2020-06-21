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

import org.emp.emphealth.core.dataTypes.Metier;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "praticien")
public class Praticien {
    
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_personne")
	private String IDPersonne;
    
	@ManyToOne
	@JoinColumn(name="metier")
    private Metier metier;
	
	@JsonIgnore
	@OneToMany(mappedBy="praticien")
    private List<PraticienTraitePatient> patients;

	@JsonIgnore
	@OneToMany(mappedBy="praticien")
    private List<Capteur> capteurs;

	@JsonIgnore
	@OneToMany(mappedBy="praticien")
    private List<Rendezvous> rdv;
	
	@JsonIgnore
	@OneToMany(mappedBy="praticien")
    private List<Prescription> prescriptions;
	
	@JsonIgnore
	@OneToMany(mappedBy="demandeur")
    private List<Imagerie> images;
	
	@JsonIgnore
	@OneToMany(mappedBy="demandeur")
    private List<Examen> examens;

	public Praticien(String iDPersonne, Metier metier, List<PraticienTraitePatient> patients, List<Capteur> capteurs,
			List<Rendezvous> rdv, List<Prescription> prescriptions, List<Imagerie> images, List<Examen> examens) {
		super();
		IDPersonne = iDPersonne;
		this.metier = metier;
		this.patients = patients;
		this.capteurs = capteurs;
		this.rdv = rdv;
		this.prescriptions = prescriptions;
		this.images = images;
		this.examens = examens;
	}

	public Praticien() {
		super();
	}

	public String getIDPersonne() {
		return IDPersonne;
	}

	public void setIDPersonne(String iDPersonne) {
		IDPersonne = iDPersonne;
	}

	public Metier getMetier() {
		return metier;
	}

	public void setMetier(Metier metier) {
		this.metier = metier;
	}

	public List<PraticienTraitePatient> getPatients() {
		return patients;
	}

	public void setPatients(List<PraticienTraitePatient> patients) {
		this.patients = patients;
	}

	public List<Capteur> getCapteurs() {
		return capteurs;
	}

	public void setCapteurs(List<Capteur> capteurs) {
		this.capteurs = capteurs;
	}

	public List<Rendezvous> getRdv() {
		return rdv;
	}

	public void setRdv(List<Rendezvous> rdv) {
		this.rdv = rdv;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<Imagerie> getImages() {
		return images;
	}

	public void setImages(List<Imagerie> images) {
		this.images = images;
	}

	public List<Examen> getExamens() {
		return examens;
	}

	public void setExamens(List<Examen> examens) {
		this.examens = examens;
	}

	
}
