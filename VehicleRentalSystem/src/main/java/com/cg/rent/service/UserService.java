package com.cg.rent.service;

import java.util.List;

import com.cg.rent.exception.ResourceNotFoundException;
import com.cg.rent.model.Booking;
import com.cg.rent.model.Enquiry;
import com.cg.rent.model.User;
import com.cg.rent.model.Vehicle;

/**
 * UserService class
 * This class handles all User related tasks
 * @author 15BW089AX
 *
 */

public interface UserService {
	
	public List<Vehicle> searchVehicleByBrand(String vbrand) throws ResourceNotFoundException;
	
	public List<Vehicle> searchVehicleByType(String vtype) throws ResourceNotFoundException;
	
	public Booking bookVehicle(Booking booking);
	
	public User updateUser(int UId, User user);
	
	public void createEnquiry(Enquiry enquiry);

}
