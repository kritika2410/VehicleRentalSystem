package com.cg.rent.service;

import java.util.List;

import com.cg.rent.exception.ResourceNotFoundException;
import com.cg.rent.model.Booking;
import com.cg.rent.model.Enquiry;
import com.cg.rent.model.User;
import com.cg.rent.model.Vehicle;

/**
 * AdminService Interface 
 * This interface have all the methods related to Admin
 * @author 15BW089AX
 *
 */

public interface AdminService {
	
	public Vehicle createVehicle(Vehicle vehicle);

	public Vehicle editVehicle(int VId, Vehicle vehicle) throws ResourceNotFoundException ;
	
	public boolean removeVehicle(int VId) throws ResourceNotFoundException;
	
	public Booking confirmBooking(int Eid);
	
	public Booking cancelBooking(int Eid) ;
	
	public List<User> fetchAllUser();
	
	public List<Enquiry> fetchAllEnquiry();
	
	public List<Booking> fetchAllBooking();
	
	public Booking fetchBookingById(int BId);
	
	public Enquiry respondEnquiry(int EId, Enquiry enquiry) throws ResourceNotFoundException;
}
