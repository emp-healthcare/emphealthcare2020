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
@Table(name = "vitals")
public class Vitals {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_vitals")
    private String IDVitals;
	
	@Column(name = "date")
    private Timestamp date;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;
    
	@ManyToOne
	@JoinColumn(name="id_infirmier")
    private Praticien praticien;
	
	@Column(name = "poids")
    private String poids;
	
	@Column(name = "taille")
    private String taille;
	
	@Column(name = "temperature")
    private String temperature;
	
	@Column(name = "pouls")
    private String pouls;
	
	@Column(name = "respiration")
    private String respiration;
	
	@Column(name = "IMC")
    private String imc;
	
	@Column(name = "tour_de_taille")
    private String tourDeTaille;
	
	@Column(name = "tour_de_tete")
    private String tourDeTete;
	
	@Column(name = "tension")
    private String tension;
	
	@Column(name = "glycemie")
    private String glycemie;
	
	@Column(name = "commentaire")
    private String commentaire;
	
	@Column(name = "statut")
    private String statut;
	
	@Column(name = "nom_infirmier")
    private String nomInfirmier;


	public Vitals(String iDVitals, Timestamp date, Patient patient, Praticien praticien, String poids, String taille,
			String temperature, String pouls, String respiration, String imc, String tourDeTaille, String tourDeTete,
			String tension, String glycemie, String commentaire, String statut, String nomInfirmier) {
		super();
		IDVitals = iDVitals;
		this.date = date;
		this.patient = patient;
		this.praticien = praticien;
		this.poids = poids;
		this.taille = taille;
		this.temperature = temperature;
		this.pouls = pouls;
		this.respiration = respiration;
		this.imc = imc;
		this.tourDeTaille = tourDeTaille;
		this.tourDeTete = tourDeTete;
		this.tension = tension;
		this.glycemie = glycemie;
		this.commentaire = commentaire;
		this.statut = statut;
		this.nomInfirmier = nomInfirmier;
	}

	public Vitals() {
		super();
	}

	public String getIDVitals() {
		return IDVitals;
	}

	public void setIDVitals(String iDVitals) {
		IDVitals = iDVitals;
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

	public String getPoids() {
		return poids;
	}

	public void setPoids(String poids) {
		this.poids = poids;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getPouls() {
		return pouls;
	}

	public void setPouls(String pouls) {
		this.pouls = pouls;
	}

	public String getRespiration() {
		return respiration;
	}

	public void setRespiration(String respiration) {
		this.respiration = respiration;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public String getTourDeTaille() {
		return tourDeTaille;
	}

	public void setTourDeTaille(String tourDeTaille) {
		this.tourDeTaille = tourDeTaille;
	}

	public String getTourDeTete() {
		return tourDeTete;
	}

	public void setTourDeTete(String tourDeTete) {
		this.tourDeTete = tourDeTete;
	}

	public String getTension() {
		return tension;
	}

	public void setTension(String tension) {
		this.tension = tension;
	}

	public String getGlycemie() {
		return glycemie;
	}

	public void setGlycemie(String glycemie) {
		this.glycemie = glycemie;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getNomInfirmier() {
		return nomInfirmier;
	}

	public void setNomInfirmier(String nomInfirmier) {
		this.nomInfirmier = nomInfirmier;
	}

	
}
