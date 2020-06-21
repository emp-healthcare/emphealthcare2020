/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.emphealth.core.resources;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.emp.emphealth.core.dataTypes.Adresse;
import org.emp.emphealth.core.dataTypes.ParamsCLPKC;
import org.emp.emphealth.core.dataTypes.Photo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "personne")
public class Personne{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_personne")
    private String IDPersonne;
    
	@Column(name = "nom")
    private String nom;
    
	@Column(name = "prenom")
    private String prenom;
    
	@Column(name = "genre")
    private String genre;
    
	@Column(name = "date_naissance")
    private Date dateNaissance;
    
	@Column(name = "statut_matrimonial")
    private String statutMatrimonial;
    
	@Column(name = "numero_telephone")
    private String numeroTelephone;
    
	@ManyToOne
	@JoinColumn(name="id_adresse")
    private Adresse adresse;
    
	@Column(name = "username")
    private String username;
    
	@Column(name = "password")
    private String password;
    
	@Column(name = "date_creation")
    private Timestamp dateCreation;
    
	@ManyToOne
	@JoinColumn(name="url_photo")
    private Photo photo;
    
	@ManyToOne
	@JoinColumn(name="url_params")
    private ParamsCLPKC params;
    
	@ManyToOne
	@JoinColumn(name="role")
    private Role role;
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy="emetteur")
    private List<Message> messagesemis;
	
	@JsonIgnore
	@OneToMany(mappedBy="recepteur")
    private List<Message> messagesrecus;

	public Personne(String iDPersonne, String nom, String prenom, String genre, Date dateNaissance,
			String statutMatrimonial, String numeroTelephone, Adresse adresse, String username, String password,
			Timestamp dateCreation, Photo photo, ParamsCLPKC params, Role role, List<Message> messagesemis,
			List<Message> messagesrecus) {
		super();
		IDPersonne = iDPersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
		this.dateNaissance = dateNaissance;
		this.statutMatrimonial = statutMatrimonial;
		this.numeroTelephone = numeroTelephone;
		this.adresse = adresse;
		this.username = username;
		this.password = password;
		this.dateCreation = dateCreation;
		this.photo = photo;
		this.params = params;
		this.role = role;
		this.messagesemis = messagesemis;
		this.messagesrecus = messagesrecus;
	}
	
	public String nomComplet() {
		return nom + " " + prenom;
	}

	public Personne() {
		super();
	}

	public String getIDPersonne() {
		return IDPersonne;
	}

	public void setIDPersonne(String iDPersonne) {
		IDPersonne = iDPersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getStatutMatrimonial() {
		return statutMatrimonial;
	}

	public void setStatutMatrimonial(String statutMatrimonial) {
		this.statutMatrimonial = statutMatrimonial;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public ParamsCLPKC getParams() {
		return params;
	}

	public void setParams(ParamsCLPKC params) {
		this.params = params;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Message> getMessagesemis() {
		return messagesemis;
	}

	public void setMessagesemis(List<Message> messagesemis) {
		this.messagesemis = messagesemis;
	}

	public List<Message> getMessagesrecus() {
		return messagesrecus;
	}

	public void setMessagesrecus(List<Message> messagesrecus) {
		this.messagesrecus = messagesrecus;
	}

	
}
