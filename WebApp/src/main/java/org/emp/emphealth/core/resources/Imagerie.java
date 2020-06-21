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

@Entity
@Table(name = "imagerie")
public class Imagerie {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_imagerie")
    private String IDImagerie;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="id_demandeur")
    private Praticien demandeur;
	
	@ManyToOne
	@JoinColumn(name="id_responsable")
    private Praticien responsable;
	
	
	@Column(name = "statut")
    private String statut;
	
	@Column(name = "type")
    private String type;
	
	@Column(name = "url")
    private String url;
	
	@Column(name = "nom_responsable")
    private String nomResponsable;
	
	@Column(name = "date")
    private Timestamp date;

	public Imagerie(String iDImagerie, Patient patient, Praticien demandeur, Praticien responsable, String statut,
			String type, String url, String nomResponsable, Timestamp date) {
		super();
		IDImagerie = iDImagerie;
		this.patient = patient;
		this.demandeur = demandeur;
		this.responsable = responsable;
		this.statut = statut;
		this.type = type;
		this.url = url;
		this.nomResponsable = nomResponsable;
		this.date = date;
	}

	public Imagerie() {
		super();
	}

	public String getIDImagerie() {
		return IDImagerie;
	}

	public void setIDImagerie(String iDImagerie) {
		IDImagerie = iDImagerie;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Praticien getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Praticien demandeur) {
		this.demandeur = demandeur;
	}

	public Praticien getResponsable() {
		return responsable;
	}

	public void setResponsable(Praticien responsable) {
		this.responsable = responsable;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNomResponsable() {
		return nomResponsable;
	}

	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	
}
