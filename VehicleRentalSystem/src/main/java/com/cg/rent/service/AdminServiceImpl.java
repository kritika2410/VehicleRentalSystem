package com.cg.rent.service;

import java.util.List;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.rent.dao.BookingDAO;
import com.cg.rent.dao.EnquiryDAO;
import com.cg.rent.dao.UserDAO;
import com.cg.rent.dao.VehicleDAO;
import com.cg.rent.exception.ResourceNotFoundException;
import com.cg.rent.model.Booking;
import com.cg.rent.model.Enquiry;
import com.cg.rent.model.User;
import com.cg.rent.model.Vehicle;

/**
 * AdminService class :
 * The class performs services related to Admin. 
 * 
 * @author 15BW089AX
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);
	
	@Autowired
	VehicleDAO vehicleDAO;
	
	@Autowired
	BookingDAO bookingDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	EnquiryDAO enquiryDAO;
	
	/**
	 * createVehicle()
	 * This method accepts vehicle instance  
	 * And stores it in database
	 * @param Vehicle instance
	 * @return void
	 */
	public Vehicle createVehicle(Vehicle vehicle) 
	{
		logger.info(" Entered service-(createVehicle)");
		logger.info(" Completed service-(createVehicle)");
		return  vehicleDAO.save(vehicle);
	}
	
	/**
	 * To fetch vehicle details by Id.
	 * @param VId
	 * @return
	 */
	public Vehicle findVehicleById(int VId) 
	{
		logger.info(" Entered service-(findVehicleById)");
		logger.info(" Completed service-(findVehicleById)");
		return vehicleDAO.findById(VId).get();
	}

	
	/**
	 * editVehicle() 
	 * This method edits details of given vehicle Id
	 * @param int VId
	 * @param Vehicle instance
	 * @return Vehicle instance
	 */
	public Vehicle editVehicle(int VId, Vehicle vehicle)throws ResourceNotFoundException 
	{
		logger.info(" Entered service-(editVehicle)");
		vehicleDAO.findById(VId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle is not present."));
		vehicleDAO.save(vehicle);
		logger.info(" Completed service-(editVehicle)");

		return vehicle;
	}
	
	/**
	 * removeVehicle()
	 * This method will remove Vehicle from database having given Id
	 * @param VId : int Vehicle Id
	 * @return void
	 */
	public boolean removeVehicle(int VId)throws ResourceNotFoundException
	{
		logger.info(" Entered service-(removeVehicle)");
		Vehicle vehicle = vehicleDAO.findById(VId)
		.orElseThrow(() -> new ResourceNotFoundException("Vehicle is not present."));
		
		vehicleDAO.delete(vehicle);
		
		logger.info(" Completed service-(removeVehicle)");
		return true;
	}
	/**
	 * fetchAllBooking();
	 * To fetch all the bookings.
	 * @return List of booking
	 */
	public List<Booking> fetchAllBookings() 
	{
		logger.info(" Entered service-(fetchAllBooking)");
		logger.info(" Completed service-(fetchAllBooking)");
		return bookingDAO.findAll();
	}

	/**
	 * fetchBookingById()
	 * This method will search booking by Id
	 * @param BId int Booking Id
	 * @return Booking Instance
	 */
	public Booking fetchBookingById(int BId)
	{
		logger.info(" Entered service-(fetchBookingById)");
		Optional<Booking> bookingOptional = bookingDAO.findById(BId);
		Booking booking = bookingOptional.get();
		logger.info(" Completed service-(fetchBookingById)");
		return booking;
	}
	
	
	/**
	 * confirmBooking()
	 * This method will confirm booking.
	 * @param int BId
	 */
	public Booking confirmBooking(int BId)
	{
		logger.info(" Entered service-(confirmBooking)");
		 Optional<Booking> bookOptional = bookingDAO.findById(BId);
		 Booking booking = bookOptional.get();
		 booking.setStatus(1);
		 bookingDAO.save(booking);
		 logger.info(" Completed service-(confirmBooking)");
		 return booking;
	}
	
	/**
	 * cancelBooking()
	 * Function will cancel the user booking.
	 * @param int BId
	 */
	public Booking cancelBooking(int BId)
	{
		logger.info(" Entered service-(cancelBooking)");
		 Optional<Booking> bookOptional = bookingDAO.findById(BId);
		 Booking booking = bookOptional.get();
		 booking.setStatus(0);
		 bookingDAO.save(booking);
		 logger.info(" Completed service-(cancelBooking)");
		 return booking;
	}
	
	/**
	 * fetchUser()
	 * This method will return list of all users
	 * @return List of User 
	 */
	public List<User> fetchAllUser()
	{
		logger.info(" Entered service-(fetchAllUser)");
		List<User> userlist = userDAO.findAll();
		logger.info(" Completed service-(fetchAllUser)");
		return userlist;
	}
	/**
	 * fetchAllEnquiry()
	 * Function returns a list of enquiry.
	 * @return List of enquiry.
	 */
	public List<Enquiry> fetchAllEnquiry()
	{
		logger.info(" Entered service-(fetchAllEnquiry)");
		List<Enquiry> enquiryList = enquiryDAO.findAll();
		logger.info(" Completed service-(fetchAllEnquiry)");
		return enquiryList;
	}
	/**
	 * fetchAllBooking()
	 * Function returns a list of booking.
	 * @return List of Booking.
	 */
	public List<Booking> fetchAllBooking()
	{
		logger.info(" Entered service-(fetchAllBooking)");
		List<Booking> bookingList = bookingDAO.findAll();
		logger.info(" Completed service-(fetchAllBooking)");
		return bookingList;
	}
	/**
	 * getUserId() : This method will return User by Id
	 * @param UId
	 * @return User instance
	 */
	public User getUserById(int UId)
	{
		logger.info(" Entered service-(getUserById)");
		logger.info(" Completed service-(getUserById)");
		return userDAO.findById(UId).get();
	}
	
	
	/**
	 * respondEnquiry()
	 * This method will give response to enquiry
	 * @param int EId
	 * @param Enquiry instance
	 * @return void
	 */
	public Enquiry respondEnquiry(int EId, Enquiry enquiry) throws ResourceNotFoundException
	{
		logger.info(" Entered service-(respondEquiry)");
		Optional<Enquiry> enquiryOptional = enquiryDAO.findById(EId);
		Enquiry enquiry1 = enquiryOptional.get();
		if(enquiry1!=null)
		enquiryDAO.save(enquiry);
		logger.info(" Completed service-(respondEnquiry)");
		return enquiry;
		
	}

	

}
