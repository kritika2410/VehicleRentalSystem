package com.cg.rent.service;

import java.text.ParseException;
import java.util.List;
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
 * UserService class :
 * This class handles all User related Services.
 * @author 15BW089AX
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	VehicleDAO vehicleDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	BookingDAO bookingDAO;
	
	@Autowired
	EnquiryDAO enquiryDAO;
	
	/**
	 * searchVehicleByBrand() :
	 * This method returns list of all vehicles with given brand.
	 * @param vbrand : String Vehicle Brand
	 * @return List<Vehicle> of Vehicles
	 */
	public List<Vehicle> searchVehicleByBrand(String vbrand)throws ResourceNotFoundException
	{
		List<Vehicle> list = vehicleDAO.findVehicleByBrand(vbrand);
		if(list.isEmpty())
			throw new ResourceNotFoundException("No Vehicle Found for brand "+vbrand);
		return list;
	}
	
	/**
	 * searchVehicleByType() :
	 * This method returns list of all vehicles with given type.
	 * @param vtype : String Vehicle Type
	 * @return List<Vehicle> of Vehicles
	 */
	public List<Vehicle> searchVehicleByType(String vtype)throws ResourceNotFoundException
	{
		List<Vehicle> list = vehicleDAO.findVehicleByType(vtype);
		if(list.isEmpty())
			throw new ResourceNotFoundException("No Vehicle Found for type "+vtype);
		return list;
	}
	
	/**
	 * bookVehicle() :
	 * This method books a vehicle for user.
	 * @param VId : int Vehicle Id
	 * @param UId : int User Id
	 * @return Booking instance
	 * @throws ParseException
	 */
	public Booking bookVehicle(Booking booking) 
	{
		Booking booking1 = bookingDAO.save(booking); 
		return booking1;
	}
	
	/**
	 * createUser() : this method will store user instance in database.
	 * @param user
	 * @return User instance
	 */
	public User createUser(User user)
	{
		return userDAO.save(user);
	}
	
	/**
	 * updateUser() :
	 * This method updated User in database.
	 * @param UId : int User Id
	 * @return Updated User instance
	 * @throws IOException
	 */
	public User updateUser(int UId, User user) 
	{
		User user1 = userDAO.findById(UId).get();
		
		if(user1!=null)
		{
			user1.setName(user.getName());
			user1.setEmail(user.getEmail());
			user1.setAddress(user.getAddress());
			user1.setContact(user.getContact());
			user1.setPassword(user.getPassword());
			user = userDAO.saveAndFlush(user1);
		}
		return user;
	}
	
	public User findUserByEmail(String email)
	{
		User user = userDAO.findUserByEmail(email);
		return user;
	}

	/**
	 * createEnquiry()
	 * This method will add Enquiry instance 
	 * @param Enquiry instance
	 * @return void
	 */
	@Override
	public void createEnquiry(Enquiry enquiry) {

		enquiryDAO.save(enquiry);
	}
	
}
