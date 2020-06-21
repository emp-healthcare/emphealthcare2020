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

import org.emp.emphealth.core.dataTypes.Allergene;

@Entity
@Table(name = "allergie")
public class Allergie {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_allergie")
    private String IDAllergie;
    
	@ManyToOne
	@JoinColumn(name="allergene")
    private Allergene allergene;
    
	@Column(name = "date_ajout")
    private Timestamp dateAjout;
    
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;
    
	@Column(name = "commentaire")
    private String commentaire;
	
	@Column(name = "severite")
    private String severite;

	public Allergie(String iDCapteur, Allergene allergene, Timestamp dateAjout, Patient patient,
			String commentaire, String severite) {
		super();
		IDAllergie = iDCapteur;
		this.allergene = allergene;
		this.dateAjout = dateAjout;
		this.patient = patient;
		this.commentaire = commentaire;
		this.severite = severite;
	}

	public Allergie() {
		super();
	}

	public String getIDCapteur() {
		return IDAllergie;
	}

	public void setIDCapteur(String iDCapteur) {
		IDAllergie = iDCapteur;
	}

	public Allergene getAllergene() {
		return allergene;
	}

	public void setAllergene(Allergene allergene) {
		this.allergene = allergene;
	}

	

	public Timestamp getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Timestamp dateAjout) {
		this.dateAjout = dateAjout;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getSeverite() {
		return severite;
	}

	public void setSeverite(String severite) {
		this.severite = severite;
	}
    
	
	
    
}
