package com.cg.rent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * User class is POJO class for User/Admin.
 * @author 15BW089AX
 *
 */
@Entity
@Table(name = "Reg_User")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)		
	private int UId;
	
	@Column(unique = true, length=50)
	@Email(message="Email is not valid")
	private String email;
	
	@Column(name="Uname", length=50)
	private String name;
	
	@Column(length = 10)
	@NotNull(message = "Contact can not be null")
	private String contact;
	
	private String address;
	
	@Column(name="pass", length=20)
	@Size(min=4, max=20, message = "Password must be between 4 to 20 characters")
	private String password;
	
	@Column(name="user_role", length=10)
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getUId() {
		return UId;
	}
	public void setUId(int UId) {
		this.UId = UId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
