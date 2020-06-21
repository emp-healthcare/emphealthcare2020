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
@Table(name = "examen")
public class Examen {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_examen")
    private String IDExamen;
	
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
	
	@Column(name = "resultat")
    private String resultat;
	
	@Column(name = "nom_responsable")
    private String nomResponsable;
	
	@Column(name = "date")
    private Timestamp date;

	public Examen(String iDExamen, Patient patient, Praticien demandeur, Praticien responsable, String statut,
			String type, String resultat, String nomResponsable, Timestamp date) {
		super();
		IDExamen = iDExamen;
		this.patient = patient;
		this.demandeur = demandeur;
		this.responsable = responsable;
		this.statut = statut;
		this.type = type;
		this.resultat = resultat;
		this.nomResponsable = nomResponsable;
		this.date = date;
	}

	public Examen() {
		super();
	}

	public String getIDExamen() {
		return IDExamen;
	}

	public void setIDExamen(String iDExamen) {
		IDExamen = iDExamen;
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

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
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
