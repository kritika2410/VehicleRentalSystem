package com.cg.rent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Enquiry class is POJO class.
 * @author 15BW089AX
 *
 */
@Entity
public class Enquiry {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int EId;
	private String email;
	private String details;
	private String response;
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getEId() {
		return EId;
	}
	public void setEId(int eId) {
		EId = eId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
