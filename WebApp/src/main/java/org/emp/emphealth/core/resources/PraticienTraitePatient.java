/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import org.emp.emphealth.core.resources.Patient;
import org.emp.emphealth.core.resources.Praticien;

/**
 *
 * @author ambara
 */
@Entity
@Table(name = "praticientraitepatient")
public class PraticienTraitePatient {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_droit")
    private int IDDroit;
    
	@ManyToOne
	@JoinColumn(name="id_praticien")
    private Praticien praticien;
    
	@ManyToOne
	@JoinColumn(name="id_patient")
    private Patient patient;
    
	@Column(name = "date_debut")
    private Timestamp dateDebut;
    
	@Column(name = "date_fin")
    private Timestamp dateFin;
    
	@Column(name = "diagnostic_principal")
    private String diagnosticPrincipal;

    public PraticienTraitePatient(int IDDroit, Praticien praticien, Patient patient, Timestamp dateDebut, Timestamp dateFin, String diagnosticPrincipal) {
        this.IDDroit = IDDroit;
        this.praticien = praticien;
        this.patient = patient;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.diagnosticPrincipal = diagnosticPrincipal;
    }

    public PraticienTraitePatient() {
    }

    public int getIDDroit() {
        return IDDroit;
    }

    public void setIDDroit(int IDDroit) {
        this.IDDroit = IDDroit;
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

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public String getDiagnosticPrincipal() {
        return diagnosticPrincipal;
    }

    public void setDiagnosticPrincipal(String diagnosticPrincipal) {
        this.diagnosticPrincipal = diagnosticPrincipal;
    }

	@Override
	public String toString() {
		return "PraticienTraitePatient [IDDroit=" + IDDroit + ", praticien=" + praticien + ", patient=" + patient
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", diagnosticPrincipal=" + diagnosticPrincipal
				+ "]";
	}
    
    
    
    
}
