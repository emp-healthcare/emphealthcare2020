package org.emp.emphealth.core.resources;

import java.sql.Date;
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
@Table(name = "rendezvous")
public class Rendezvous {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_consultation")
    private String IDConsultation;
	
	@Column(name = "motif")
    private String motif;
    
	@Column(name = "date")
    private Date date;
    
	@Column(name = "accepte")
    private String accepte;
    
	@Column(name = "effectue")
    private String effectue;
	
	@Column(name = "date_demande")
    private Timestamp dateDemande;
	
	@ManyToOne
	@JoinColumn(name="id_praticien")
    private Praticien praticien;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;

	public Rendezvous(String iDConsultation, String motif, Date date, String accepte, String effectue,
			Timestamp dateDemande, Praticien praticien, Patient patient) {
		super();
		IDConsultation = iDConsultation;
		this.motif = motif;
		this.date = date;
		this.accepte = accepte;
		this.effectue = effectue;
		this.dateDemande = dateDemande;
		this.praticien = praticien;
		this.patient = patient;
	}

	public Rendezvous() {
		super();
	}

	public String getIDConsultation() {
		return IDConsultation;
	}

	public void setIDConsultation(String iDConsultation) {
		IDConsultation = iDConsultation;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAccepte() {
		return accepte;
	}

	public void setAccepte(String accepte) {
		this.accepte = accepte;
	}

	public String getEffectue() {
		return effectue;
	}

	public void setEffectue(String effectue) {
		this.effectue = effectue;
	}

	public Timestamp getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Timestamp dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
}
