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
@Table(name = "prescription")
public class Prescription {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_prescription")
    private String IDPrescription;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="id_praticien")
    private Praticien praticien;
	
	@Column(name = "date")
    private Timestamp date;
	
	@Column(name = "statut")
    private String statut;
	
	@Column(name = "med1")
    private String med1;
	
	@Column(name = "med2")
    private String med2;
	
	@Column(name = "med3")
    private String med3;
	
	@Column(name = "med4")
    private String med4;
	
	@Column(name = "med5")
    private String med5;
	
	@Column(name = "pos1")
    private String pos1;
	
	@Column(name = "pos2")
    private String pos2;
	
	@Column(name = "pos3")
    private String pos3;
	
	@Column(name = "pos4")
    private String pos4;
	
	@Column(name = "pos5")
    private String pos5;
	
	@Column(name = "traitement")
    private String traitement;

	public Prescription(String iDPrescription, Patient patient, Praticien praticien, Timestamp date, String statut,
			String med1, String med2, String med3, String med4, String med5, String pos1, String pos2, String pos3,
			String pos4, String pos5, String traitement) {
		super();
		IDPrescription = iDPrescription;
		this.patient = patient;
		this.praticien = praticien;
		this.date = date;
		this.statut = statut;
		this.med1 = med1;
		this.med2 = med2;
		this.med3 = med3;
		this.med4 = med4;
		this.med5 = med5;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.pos3 = pos3;
		this.pos4 = pos4;
		this.pos5 = pos5;
		this.traitement = traitement;
	}

	public Prescription() {
		super();
	}

	public String getIDPrescription() {
		return IDPrescription;
	}

	public void setIDPrescription(String iDPrescription) {
		IDPrescription = iDPrescription;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getMed1() {
		return med1;
	}

	public void setMed1(String med1) {
		this.med1 = med1;
	}

	public String getMed2() {
		return med2;
	}

	public void setMed2(String med2) {
		this.med2 = med2;
	}

	public String getMed3() {
		return med3;
	}

	public void setMed3(String med3) {
		this.med3 = med3;
	}

	public String getMed4() {
		return med4;
	}

	public void setMed4(String med4) {
		this.med4 = med4;
	}

	public String getMed5() {
		return med5;
	}

	public void setMed5(String med5) {
		this.med5 = med5;
	}

	public String getPos1() {
		return pos1;
	}

	public void setPos1(String pos1) {
		this.pos1 = pos1;
	}

	public String getPos2() {
		return pos2;
	}

	public void setPos2(String pos2) {
		this.pos2 = pos2;
	}

	public String getPos3() {
		return pos3;
	}

	public void setPos3(String pos3) {
		this.pos3 = pos3;
	}

	public String getPos4() {
		return pos4;
	}

	public void setPos4(String pos4) {
		this.pos4 = pos4;
	}

	public String getPos5() {
		return pos5;
	}

	public void setPos5(String pos5) {
		this.pos5 = pos5;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	
}
