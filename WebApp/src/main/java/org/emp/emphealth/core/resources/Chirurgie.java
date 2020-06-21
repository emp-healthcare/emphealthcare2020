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
@Table(name = "chirurgie")
public class Chirurgie {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_chirurgie")
    private String IDChirurgie;
    
	@Column(name = "description")
    private String description;
    
	@Column(name = "cause")
    private String cause;
    
	@Column(name = "date")
    private Timestamp date;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="id_praticien")
    private Praticien praticien;

	public Chirurgie(String iDChirurgie, String description, String cause, Timestamp date, Patient patient,
			Praticien praticien) {
		super();
		IDChirurgie = iDChirurgie;
		this.description = description;
		this.cause = cause;
		this.date = date;
		this.patient = patient;
		this.praticien = praticien;
	}

	public Chirurgie() {
		super();
	}

	public String getIDChirurgie() {
		return IDChirurgie;
	}

	public void setIDChirurgie(String iDChirurgie) {
		IDChirurgie = iDChirurgie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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
