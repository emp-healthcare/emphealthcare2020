package org.emp.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Identifiant {
	
	private  String idsensor;
	private  String idChiffre;
	private  String timestamp;
	private  String hash;
	public Identifiant(String idsensor, String idChiffre, String timestamp, String hash) {
		super();
		this.idsensor = idsensor;
		this.idChiffre = idChiffre;
		this.timestamp = timestamp;
		this.hash = hash;
	}
	public Identifiant() {
		super();
	}
	public String getIdsensor() {
		return idsensor;
	}
	public void setIdsensor(String idsensor) {
		this.idsensor = idsensor;
	}
	public String getIdChiffre() {
		return idChiffre;
	}
	public void setIdChiffre(String idChiffre) {
		this.idChiffre = idChiffre;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
}
