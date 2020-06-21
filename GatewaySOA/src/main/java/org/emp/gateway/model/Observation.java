/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gateway.model;

/**
 *
 * @author blunt
 */
public class Observation {
    private String idobservation;
    //private Dispositif dispositif;
    private String date;
    private String heure;
    private String valeur;

    public Observation(String idobservation, String date, String heure, String valeur) {
        this.idobservation = idobservation;
        //this.dispositif = dispositif;
        this.date = date;
        this.heure = heure;
        this.valeur = valeur;
    }

    public Observation() {
    }

    public String getIdobservation() {
        return idobservation;
    }

    public void setIdobservation(String idobservation) {
        this.idobservation = idobservation;
    }

  

 
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

}
