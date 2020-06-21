/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.emphealth.core.dataTypes;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "photos")
public class Photo {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "url_photo")
    private String urlPhoto;
    
	@Column(name = "date_creation")
    private Timestamp dateCreation;

    public Photo(String urlPhoto, Timestamp dateCreation) {
        this.urlPhoto = urlPhoto;
        this.dateCreation = dateCreation;
    }

    public Photo() {
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
}
