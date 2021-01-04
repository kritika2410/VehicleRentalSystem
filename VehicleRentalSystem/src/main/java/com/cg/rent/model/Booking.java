package com.cg.rent.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
/**
 * Booking class is POJO class having all booking details.
 * @author 15BW089AX
 *
 */
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int BId;
	@OneToOne
	@JoinColumn(name = "UId_FK")
	private User user;
	@OneToOne
	@JoinColumn(name = "VId_FK")
	private Vehicle vehicle;
	private Date toDate;
	private Date fromDate;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public int getBId() {
		return BId;
	}
	public void setBId(int bId) {
		BId = bId;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String toString()
	{
		return BId+"\t"+vehicle.getVbrand()+"\t"+vehicle.getVNumber()+"\t"+fromDate+"\t"+toDate;
	}
}
