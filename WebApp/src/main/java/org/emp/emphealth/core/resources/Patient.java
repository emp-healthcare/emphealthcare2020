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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "patient")
public class Patient{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_personne")
	private String IDPersonne;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<PraticienTraitePatient> praticiens;
    
	@Column(name = "taille")
    private String taille;
	
	@Column(name = "poids")
    private String poids;
	
	@Column(name = "groupe_sanguin")
    private String groupeSanguin;
	
	@Column(name = "adresse_gateway")
    private String adresseGateway;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<Capteur> capteurs;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<Rendezvous> rdv;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<Allergie> allergies;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<Chirurgie> chirurgie;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<Dentspb> dentspb;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<Vitals> vitals;
	
	@JsonIgnore
	@OneToMany(mappedBy="patient")
    private List<Prescription> prescriptions;
	
	

	public Patient() {
		super();
	}

	public Patient(String iDPersonne, List<PraticienTraitePatient> praticiens, String taille, String poids,
			String groupeSanguin, String adresseGateway, List<Capteur> capteurs, List<Rendezvous> rdv,
			List<Allergie> allergies, List<Chirurgie> chirurgie, List<Dentspb> dentspb, List<Vitals> vitals,
			List<Prescription> prescriptions) {
		super();
		IDPersonne = iDPersonne;
		this.praticiens = praticiens;
		this.taille = taille;
		this.poids = poids;
		this.groupeSanguin = groupeSanguin;
		this.adresseGateway = adresseGateway;
		this.capteurs = capteurs;
		this.rdv = rdv;
		this.allergies = allergies;
		this.chirurgie = chirurgie;
		this.dentspb = dentspb;
		this.vitals = vitals;
		this.prescriptions = prescriptions;
	}

	public String getIDPersonne() {
		return IDPersonne;
	}

	public void setIDPersonne(String iDPersonne) {
		IDPersonne = iDPersonne;
	}

	public List<PraticienTraitePatient> getPraticiens() {
		return praticiens;
	}

	public void setPraticiens(List<PraticienTraitePatient> praticiens) {
		this.praticiens = praticiens;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getPoids() {
		return poids;
	}

	public void setPoids(String poids) {
		this.poids = poids;
	}

	public String getGroupeSanguin() {
		return groupeSanguin;
	}

	public void setGroupeSanguin(String groupeSanguin) {
		this.groupeSanguin = groupeSanguin;
	}

	public String getAdresseGateway() {
		return adresseGateway;
	}

	public void setAdresseGateway(String adresseGateway) {
		this.adresseGateway = adresseGateway;
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

	public List<Allergie> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Allergie> allergies) {
		this.allergies = allergies;
	}

	public List<Chirurgie> getChirurgie() {
		return chirurgie;
	}

	public void setChirurgie(List<Chirurgie> chirurgie) {
		this.chirurgie = chirurgie;
	}

	public List<Dentspb> getDentspb() {
		return dentspb;
	}

	public void setDentspb(List<Dentspb> dentspb) {
		this.dentspb = dentspb;
	}

	public List<Vitals> getVitals() {
		return vitals;
	}

	public void setVitals(List<Vitals> vitals) {
		this.vitals = vitals;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	
}
