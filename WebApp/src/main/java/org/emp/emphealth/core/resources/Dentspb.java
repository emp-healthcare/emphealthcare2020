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
@Table(name = "dents")
public class Dentspb {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_dents")
    private String IDDents;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;
	
	@Column(name = "date")
    private Timestamp date;
	
	@Column(name = "nom_dentiste")
    private String dentiste;
	
	@Column(name = "maladie")
    private String maladie;
	
	@Column(name = "commentaire")
    private String commentaire;

	public Dentspb(String iDDents, Patient patient, Timestamp date, String dentiste, String maladie,
			String commentaire) {
		super();
		IDDents = iDDents;
		this.patient = patient;
		this.date = date;
		this.dentiste = dentiste;
		this.maladie = maladie;
		this.commentaire = commentaire;
	}

	public Dentspb() {
		super();
	}

	public String getIDDents() {
		return IDDents;
	}

	public void setIDDents(String iDDents) {
		IDDents = iDDents;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDentiste() {
		return dentiste;
	}

	public void setDentiste(String dentiste) {
		this.dentiste = dentiste;
	}

	public String getMaladie() {
		return maladie;
	}

	public void setMaladie(String maladie) {
		this.maladie = maladie;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
}
