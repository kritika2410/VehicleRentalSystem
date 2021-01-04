package com.cg.rent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Vehicle class is POJO class.
 * @author 15BW089AX
 *
 */

@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int VId;
	private String VNumber;
	private String Vbrand;
	private String VType;
	public int getVId() {
		return VId;
	}
	public void setVId(int vId) {
		VId = vId;
	}
	public String getVNumber() {
		return VNumber;
	}
	public void setVNumber(String vNumber) {
		VNumber = vNumber;
	}
	public String getVbrand() {
		return Vbrand;
	}
	public void setVbrand(String vbrand) {
		Vbrand = vbrand;
	}
	public String getVType() {
		return VType;
	}
	public void setVType(String vType) {
		VType = vType;
	}
	
	public String toString()
	{
		return VId+"\t"+VNumber+"\t"+Vbrand+"\t"+VType;
		
	}

}
