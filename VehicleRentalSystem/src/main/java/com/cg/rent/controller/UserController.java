package com.cg.rent.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cg.rent.service.UserManagementServiceImpl;
import com.cg.rent.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	UserManagementServiceImpl userManager;
	
	
	/**
	 * getVehicleByBrand() : This method will list the vehicles by brand
	 * @param brand
	 * @return List of Vehicle
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/vehicleByBrand/{brand}")
	ResponseEntity<List<Vehicle>> getVehicleByBrand(@PathVariable("brand") String brand)throws ResourceNotFoundException
	{
		List<Vehicle> list = userService.searchVehicleByBrand(brand);
		return new ResponseEntity<List<Vehicle>>(list,HttpStatus.OK);
	}

	/**
	 * getVehicleByType() : This method will list vehicles by type.
	 * @param type
	 * @return List of vehicle
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/vehicleByType/{type}")
	ResponseEntity<List<Vehicle>> getVehicleByType(@PathVariable("type") String type) throws ResourceNotFoundException
	{
		List<Vehicle> list = userService.searchVehicleByType(type);
		return new ResponseEntity<List<Vehicle>>(list,HttpStatus.OK);
	}
	
	/**
	 * userLogin() : This method performs login operation
	 * @param login
	 * @return String Message
	 */
	@PostMapping("/login")
	String userLogin(@RequestBody Login login)
	{
		String result = userManager.login(login);
		return result;
	}
	
	/**
	 * createUser() : This function will save user instance in database
	 * @param user
	 * @return User instance
	 */
	@PostMapping("/newUser")
	User createUser(@Valid @RequestBody User user)
	{
		return userManager.registerUser(user);
	}
	
	/**
	 * bookVehicle() : This method will add booking in database
	 * @param booking
	 * @return
	 */
	@PostMapping("/bookVehicle")
	String bookVehicle(@RequestBody Booking booking)
	{
		userService.bookVehicle(booking);
		return "Vehicle is booked Successfully..";
	}
	
	/**
	 * updateUser() : This method will update user 
	 * @param UID
	 * @param user
	 * @return User instance
	 */
	@PutMapping("/updateUser/{UID}")
	User updateUser(@PathVariable("UID") int UID, @RequestBody User user)
	{
		return userService.updateUser(UID, user);
	}
	
	/**
	 * addEnquiry() : This method add user enquiry
	 * @param enquiry
	 */
	@PostMapping("/addEnquiry")
	String addEnquiry(@RequestBody Enquiry enquiry)
	{
		userService.createEnquiry(enquiry);
		return "Enquiry added Successfully... ";
	}
}
