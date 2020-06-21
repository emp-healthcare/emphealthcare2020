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
@Table(name = "paramsclpkc")
public class ParamsCLPKC {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "url_params")
    private String urlParams;
    
	@Column(name = "date_creation")
    private Timestamp dateCreation;

    public ParamsCLPKC(String urlParams, Timestamp dateCreation) {
        this.urlParams = urlParams;
        this.dateCreation = dateCreation;
    }

    public ParamsCLPKC() {
    }

    public String getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
}
