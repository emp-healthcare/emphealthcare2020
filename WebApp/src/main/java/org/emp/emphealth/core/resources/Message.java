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
@Table(name = "messages")
public class Message {
	
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_message")
    private String IDMessage;
    
	@ManyToOne
	@JoinColumn(name="emetteur")
    private Personne emetteur;
    
	@ManyToOne
	@JoinColumn(name="recepteur")
    private Personne recepteur;
	
	@Column(name = "objet")
    private String objet;
	
	@Column(name = "date")
    private Timestamp date;
    
	@Column(name = "contenu")
    private String contenu;

	public Message(String iDMessage, Personne emetteur, Personne recepteur, String objet, Timestamp date,
			String contenu) {
		super();
		IDMessage = iDMessage;
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.objet = objet;
		this.date = date;
		this.contenu = contenu;
	}

	public Message() {
		super();
	}

	public String getIDMessage() {
		return IDMessage;
	}

	public void setIDMessage(String iDMessage) {
		IDMessage = iDMessage;
	}

	public Personne getEmetteur() {
		return emetteur;
	}

	public void setEmetteur(Personne emetteur) {
		this.emetteur = emetteur;
	}

	public Personne getRecepteur() {
		return recepteur;
	}

	public void setRecepteur(Personne recepteur) {
		this.recepteur = recepteur;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
}
