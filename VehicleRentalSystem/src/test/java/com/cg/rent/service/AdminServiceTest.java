package com.cg.rent.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.rent.dao.EnquiryDAO;
import com.cg.rent.dao.UserDAO;
import com.cg.rent.dao.VehicleDAO;
import com.cg.rent.exception.ResourceNotFoundException;
import com.cg.rent.dao.BookingDAO;
import com.cg.rent.model.Booking;
import com.cg.rent.model.Enquiry;
import com.cg.rent.model.User;
import com.cg.rent.model.Vehicle;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceTest {

	@MockBean
	VehicleDAO vehicleDAO;

	@MockBean
	UserDAO userDAO;

	@MockBean
	EnquiryDAO enquiryDAO;

	@MockBean
	BookingDAO bookingDAO;

	@Autowired
	AdminService adminService;

	/**
	 * testCreateVehicle()
	 * testing if vehicle is created.
	 */
	@Test
	void testCreateVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setVId(50);
		vehicle.setVNumber("MH12344");
		vehicle.setVType("Car");
		vehicle.setVbrand("Ford");

		Mockito.when(vehicleDAO.save(vehicle)).thenReturn(vehicle);
		assertThat(adminService.createVehicle(vehicle)).isEqualTo(vehicle);

	}
	/**
	 * testEditVehicle()
	 * testing if vehicle is edited.
	 * @throws ResourceNotFoundException
	 */

	@Test
	void testEditVehicle() throws ResourceNotFoundException {
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVId(60);
		vehicle2.setVbrand("ford");
		vehicle2.setVNumber("mh1234");
		vehicle2.setVType("car");
		try
		{
			Mockito.when(vehicleDAO.findById(60).get()).thenReturn(vehicle2);
			vehicle2.setVbrand("bmw");

			Mockito.when(vehicleDAO.save(vehicle2)).thenReturn(vehicle2);
			System.out.println(vehicle2.getVbrand());
			assertThat(adminService.editVehicle(60, vehicle2)).isEqualTo(vehicle2);
		}
		catch(Exception e) {}
	}

	/**
	 * testRemoveVehicle()
	 * testing if vehicle is deleted.
	 * @throws ResourceNotFoundException
	 */
	@Test
	void testRemoveVehicle() throws ResourceNotFoundException{


		Vehicle vehicle4 = new Vehicle();
		vehicle4.setVId(29);
		vehicle4.setVNumber("mh5678");
		vehicle4.setVbrand("eicher");
		vehicle4.setVType("truck");
		try
		{
			Mockito.when(vehicleDAO.findById(29).get()).thenReturn(vehicle4);
			vehicleDAO.delete(vehicle4);
			Assert.assertEquals(adminService.removeVehicle(29), true);
		}
		catch(Exception e) {}

	}

	/**
	 * testConfirmBooking()
	 * testing if booking is confirmed.
	 */

	@Test
	void testConfirmBooking() {
		Booking booking1 = new Booking();
		booking1.setBId(14);
		booking1.setFromDate(new Date());
		booking1.setToDate(new Date());
		booking1.setStatus(0);
		try
		{
			Mockito.when(bookingDAO.findById(14).get()).thenReturn(booking1);
			booking1.setStatus(1);
			Mockito.when(bookingDAO.save(booking1)).thenReturn(booking1);
			System.out.println(booking1.getStatus());
			assertThat(adminService.confirmBooking(14)).isEqualTo(booking1);
		}
		catch(Exception e) {}
	}

	/**
	 * testCancelBooking()
	 * testing if booking is cancelled.
	 */
	@Test
	void testCancelBooking() {
		Booking booking2 = new Booking();
		booking2.setBId(14);
		booking2.setFromDate(null);
		booking2.setToDate(null);
		booking2.setStatus(0);
		try
		{
			Mockito.when(bookingDAO.findById(14).get()).thenReturn(booking2);
			booking2.setStatus(0);
			Mockito.when(bookingDAO.save(booking2)).thenReturn(booking2);
			System.out.println(booking2.getStatus());
			assertThat(adminService.confirmBooking(14)).isEqualTo(booking2);
		}
		catch(Exception e) {}
	}

	/**
	 * testFetchAllUser()
	 * fetching all user.
	 */
	@Test
	void testFetchAllUser() {
		User user1 = new User();
		user1.setUId(3);
		user1.setRole("Admin");
		user1.setPassword("password");
		user1.setName("Kritika");
		user1.setEmail("fgi@gmail.com");
		user1.setContact("1234567890");
		user1.setAddress("Pune");

		User user2 = new User();
		user1.setUId(9);
		user1.setRole("Admin");
		user1.setPassword("password");
		user1.setName("Kritika");
		user1.setEmail("kritika@gmail.com");
		user1.setContact("1234567890");
		user1.setAddress("Pue");


		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);

		Mockito.when(userDAO.findAll()).thenReturn(userList);
		assertThat(adminService.fetchAllUser()).isEqualTo(userList);

	}

	/**
	 * testFetchAllEnquiry()
	 * fetching all enquiries.
	 */
	@Test
	void testFetchAllEnquiry() {
		Enquiry enquiry1= new Enquiry();
		enquiry1.setEId(21);
		enquiry1.setDetails("hey");
		enquiry1.setEmail("xyz@gmail.com");
		enquiry1.setResponse("will");

		Enquiry enquiry2 =new Enquiry();
		enquiry2.setEId(34);
		enquiry2.setDetails("I need a car");
		enquiry2.setEmail("abc@gmail.com");
		enquiry2.setResponse("string");

		List<Enquiry> enquiryList = new ArrayList<>();
		enquiryList.add(enquiry1);
		enquiryList.add(enquiry2);

		Mockito.when(enquiryDAO.findAll()).thenReturn(enquiryList);
		assertThat(adminService.fetchAllEnquiry()).isEqualTo(enquiryList);

	}

	/**
	 * testFetchBookingById()
	 * testing booking by Id.
	 */
	@Test
	void testFetchBookingById() {

		Booking booking1 = new Booking();
		booking1.setBId(12);
		booking1.setFromDate(new Date());
		booking1.setStatus(0);
		booking1.setToDate(new Date());
		booking1.setUser(new User());
		booking1.setVehicle(new Vehicle());
		try
		{
			Mockito.when(bookingDAO.fetchBookingById(12).get(12)).thenReturn(booking1);
			assertThat(adminService.fetchBookingById(29)).isEqualTo(booking1);
		}
		catch(Exception e) {}
	}

	/**
	 * testRespondEnquiry()
	 * testing if enquiry is responded.
	 */
	@Test
	void testRespondEnquiry() {
		Enquiry enquiry3= new Enquiry();
		enquiry3.setEId(35);
		enquiry3.setDetails("string");
		enquiry3.setEmail("string");
		enquiry3.setResponse("ok");

		try
		{
			Mockito.when(enquiryDAO.findById(21).get()).thenReturn(enquiry3);
			enquiry3.setResponse("working");
			Mockito.when(enquiryDAO.save(enquiry3)).thenReturn(enquiry3);
			System.out.println(enquiry3.getResponse());
			assertThat(adminService.respondEnquiry(21, enquiry3)).isEqualTo(enquiry3);
		}
		catch(Exception e) {}
	}
}


