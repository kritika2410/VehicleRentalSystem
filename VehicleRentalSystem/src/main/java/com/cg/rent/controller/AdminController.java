package com.cg.rent.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rent.exception.ResourceNotFoundException;
import com.cg.rent.model.Booking;
import com.cg.rent.model.Enquiry;
import com.cg.rent.model.Login;
import com.cg.rent.model.User;
import com.cg.rent.model.Vehicle;
import com.cg.rent.service.AdminServiceImpl;
import com.cg.rent.service.UserManagementServiceImpl;



@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	UserManagementServiceImpl userManager;
	
	
	
	/**
	 * adminLogin()
	 * To validate admin email and password.
	 * @param login
	 * @return String message
	 */
	@PostMapping("/login")
	ResponseEntity<Object> adminLogin(@RequestBody Login login)
	{
		logger.info(" Entered Admin Controller -(adminLogin)");
		String result = userManager.login(login);
		logger.info(" Completed Admin Controller -(adminLogin)");
		if(result.contains("User"))
			return new ResponseEntity<Object>("Sorry!! You are not authorized",HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
	
	
	
	/**
	 * createAdmin() : This function will save admin instance in database
	 * @param user
	 * @return User instance
	 */
	@PostMapping("/newAdmin")
	ResponseEntity<Object> createAdmin(@Valid @RequestBody User admin)
	{
		logger.info(" Entered Admin Controller -(registerNewAdmin)");
		User user= userManager.registerUser(admin);
		logger.info(" Completed Admin Controller -(registerNewAdmin)");
		if(user==null)
		return new ResponseEntity<Object>("Registration failed",HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Object>(user,HttpStatus.OK);
		
	}
	
	
	
	/**
	 * To fetch all user.
	 * @return List of Users
	 */
	@GetMapping("/getAllUsers")
	public List<User> listUsers()
	{
		logger.info(" Entered Admin Controller -(getAllUsers)");
		logger.info(" Completed Admin Controller -(getAllUsers)");
		return adminService.fetchAllUser();
	}
	
	
	/**
	 * To fetch user by ID.
	 * @param UId
	 * @return User instance
	 */
	@GetMapping("/getUserById/{UId}")
	User getUserById(@PathVariable int UId)
	{
		logger.info(" Entered Admin Controller -(getUserById)");
		logger.info(" Entered Admin Controller -(getUserById)");
		return adminService.getUserById(UId);
	}
	
	
	/**
	 * To create vehicle. 
	 * @param vehicle
	 * @return Vehicle instance
	 */
	@PostMapping("/saveVehicle")
	public ResponseEntity<Object> saveVehicle(@RequestBody final Vehicle vehicle)
	{
		logger.info(" Entered Admin Controller -(createVehicle)");
		Vehicle newVehicle= adminService.createVehicle(vehicle);
		logger.info("Completed Admin controller - (createVehicle)");
		if(newVehicle==null)
			return new ResponseEntity<Object>("Vehicle cannot be created",HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Object>(vehicle,HttpStatus.OK);
		
	}
	
	/**
	 * To delete vehicle by id.
	 * @param VId
	 */
	@DeleteMapping("/deleteVehicle/{VId}")
	public String deleteVehicle(@PathVariable int VId)throws ResourceNotFoundException
	{
		logger.info(" Entered Admin Controller -(deleteVehicle)");
		adminService.removeVehicle(VId);
		logger.info(" Completed Admin Controller -(deleteVehicle)");
		return "Vehicle deleted Successfully!!";
	}
	
	/**
	 * To edit Vehicle by Id.
	 * @param VId
	 * @param vehicle
	 * @return Vehicle instance
	 */
	@PutMapping("/editVehicle/{VId}")
	public String updateVehicle(@PathVariable int VId, @RequestBody Vehicle vehicle)throws ResourceNotFoundException
	{
		logger.info("Entered Admin Controller -(updateVehicle)");
		 adminService.editVehicle(VId, vehicle);
		 logger.info("Completed Admin Controller -(updateVehicle)");
		 return "Vehicle edited Successfully";
	}
	
	/**
	 * getAllBooking() 
	 * To get list of all bookings
	 * @return List Of booking
	 */
	@GetMapping("/getAllBookings")
	List<Booking> getAllBooking()
	{
		logger.info(" Entered Admin Controller -(getAllBooking)");
		logger.info(" Completed Admin Controller -(getAllBooking)");
		return adminService.fetchAllBooking();
	}
	/**
	 * To confirm the booking.
	 * @param BId
	 * @return
	 */
	@PutMapping("/confirmBooking/{BId}")
	public ResponseEntity<String> updateBooking(@PathVariable int BId)
	{
		logger.info(" Entered Admin Controller -(updateBooking)");
		logger.info(" Completed Admin Controller -(updateBooking)");
		if(adminService.confirmBooking(BId)!=null)
		
			return new ResponseEntity<String>("Booking confirmed.",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Booking failed",HttpStatus.BAD_REQUEST);

	}
	
	/**
	 * To cancel the booking.
	 * @param BId
	 * @return String
	 */
	@PutMapping("/cancelBooking/{BId}")
	public String cancelBooking(@PathVariable int BId)
	{
		logger.info("Entered Admin Controller -(cancelBooking)");
		 adminService.cancelBooking(BId);
		 logger.info("Completed Admin Controller -(cancelBooking)");
		 return "Booking Cancelled";
	}
	
	/**
	 * To fetch all queries.
	 * @return List of enquiries.
	 */
	@GetMapping("/getAllEnquiries")
	public List<Enquiry> listEnquiries()
	{
		logger.info(" Entered Admin Controller -(listEnquiries)");
		logger.info(" Completed Admin Controller -(listEnquiries)");
		return adminService.fetchAllEnquiry();
	}
	
	/**
	 * To respond the queries.
	 * @param EId
	 * @param enquiry
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	@PutMapping("/respondEnquiry/{EId}")
	public ResponseEntity<String> updateEnquiry(@PathVariable int EId, @RequestBody Enquiry enquiry ) throws ResourceNotFoundException
	{
		logger.info(" Entered Admin Controller -(respondEnquiry)");
		adminService.respondEnquiry(EId, enquiry);
		logger.info(" Completed Admin Controller -(respondEnquiry)");
		return new ResponseEntity<String>("Enquiry responded.",HttpStatus.OK);
		
	}
}
