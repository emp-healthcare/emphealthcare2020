package org.emp.emphealth.core.dataTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allergene")
public class Allergene {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "allergene")
	private String allergene;

	public Allergene(String allergene) {
		super();
		this.allergene = allergene;
	}

	public Allergene() {
		super();
	}

	public String getAllergene() {
		return allergene;
	}

	public void setAllergene(String allergene) {
		this.allergene = allergene;
	}
	
	
}
