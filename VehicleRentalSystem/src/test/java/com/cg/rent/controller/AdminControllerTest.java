package com.cg.rent.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.rent.model.Booking;
import com.cg.rent.model.Enquiry;
import com.cg.rent.model.User;
import com.cg.rent.model.Vehicle;
import com.cg.rent.service.AdminServiceImpl;
import com.cg.rent.service.UserManagementServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminController.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdminServiceImpl adminService;

	@MockBean
	private UserManagementServiceImpl userManager;

	/**
	 * testCreateAdmin()
	 * testing whether admin is created.
	 * @throws Exception
	 */
	@Test
	public void testCreateAdmin() throws Exception
	{
		String URI ="/admin/newAdmin";
		User user =  new User();
		user.setUId(2);
		user.setAddress("vimannagar");
		user.setContact("1234567890");
		user.setEmail("ralph@gmail.com");
		user.setName("Ralph");
		user.setRole("Admin");
		user.setPassword("password");

		String jsonInput = this.converttoJson(user);
		Mockito.when(userManager.registerUser(Mockito.any(User.class))).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
	/**
	 * testSaveVehicle()
	 * testing vehicle is being created.
	 * @throws Exception
	 */
	@Test
	public void testSaveVehicle() throws Exception {
		String URI ="/admin/saveVehicle";
		Vehicle vehicle = new Vehicle();
		vehicle.setVId(11);
		vehicle.setVbrand("Ford");
		vehicle.setVNumber("MH9075");
		vehicle.setVType("Car");
		String jsonInput = this.converttoJson(vehicle);
		Mockito.when(adminService.createVehicle(Mockito.any(Vehicle.class))).thenReturn(vehicle);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}
	/**
	 * testUpdateVehicle()
	 * testing if vehicle is updated.
	 * @throws Exception
	 */
	@Test
	public void testUpdateVehicle() throws Exception{

		String URI = "/admin/editVehicle/{VId}";
		Vehicle vehicle = new Vehicle();
		vehicle.setVId(11);
		vehicle.setVbrand("Ford");
		vehicle.setVNumber("MH9075");
		vehicle.setVType("Car");
		String jsonInput = this.converttoJson(vehicle);


		Mockito.when(adminService.editVehicle(Mockito.anyInt(),Mockito.any(Vehicle.class))).thenReturn(vehicle);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,12).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	/**
	 * testDeleteVehicle()
	 * testing if vehicle is deleted.
	 * @throws Exception
	 */

	@Test
	public void testDeleteVehicle() throws Exception{
		String URI = "/admin/deleteVehicle/{VId}";
		Vehicle vehicle = new Vehicle();
		vehicle.setVId(11);
		vehicle.setVbrand("Ford");
		vehicle.setVNumber("MH9075");
		vehicle.setVType("Car");
		Mockito.when(adminService.findVehicleById(Mockito.anyInt())).thenReturn(vehicle);
		Mockito.when(adminService.removeVehicle(Mockito.anyInt())).thenReturn(true);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 11).accept(MediaType.
				APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
	/**
	 * getUserById()
	 * Getting user by id.
	 * @throws Exception
	 */
	@Test
	public void getUserById() throws Exception{
		String URI ="/admin/getUserById/{UId}";
		User user = new User();
		user.setUId(3);
		user.setName("Kritika");
		user.setEmail("fgi@gmail.com");
		user.setContact("1234567890");
		user.setAddress("Pune");
		user.setRole("Admin");
		user.setPassword("password");
		String jsonInput = this.converttoJson(user);
		Mockito.when(adminService.getUserById(Mockito.anyInt())).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 21).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);

	}
	/**
	 * testFetchAllUser()
	 * fetching all user.
	 * @throws Exception
	 */
	@Test
	public void testFetchAllUser() throws Exception{
		String URI= "/admin/getAllUsers";


		User user = new User();
		user.setUId(1);
		user.setName("Sweety");
		user.setPassword("sweety123");
		user.setAddress("Rasipuram");
		user.setContact("9630258741");
		user.setEmail("sweety@msn.com");

		User user1 = new User();
		user.setUId(2);
		user.setName("Pooja");
		user.setPassword("Pooja123");
		user.setAddress("Salem");
		user.setContact("9630258741");
		user.setEmail("pooja@mail.com");

		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);


		String jsonInput = this.converttoJson(userList);

		Mockito.when(adminService.fetchAllUser()).thenReturn(userList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}


	/**
	 * testing if booking is updated.
	 * @throws Exception
	 */
	@Test
	public void testUpdateBooking() throws Exception {
		String URI = "/admin/confirmBooking/{BId}";
		Booking booking = new Booking();
		booking.setBId(14);
		booking.setFromDate(new Date());
		booking.setStatus(1);
		booking.setToDate(new Date());
		booking.setUser(new User());
		booking.setVehicle(new Vehicle());

		String jsonInput = this.converttoJson(booking);


		Mockito.when(adminService.confirmBooking(Mockito.anyInt())).thenReturn(booking);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,12).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	/**
	 * testGetAllBooking()
	 * testing all bookings.
	 * @throws Exception
	 */
	@Test
	public void testGetAllBooking() throws Exception{
		String URI = "/admin/getAllBookings";
		Booking booking = new Booking();
		booking.setBId(12);
		booking.setUser(new User());
		booking.setVehicle(new Vehicle());
		booking.setStatus(0);
		booking.setFromDate(new Date());
		booking.setToDate(new Date());

		Booking booking1 = new Booking();
		booking1.setBId(13);
		booking1.setUser(new User());
		booking1.setVehicle(new Vehicle());
		booking1.setStatus(0);
		booking1.setFromDate(new Date());
		booking1.setToDate(new Date());

		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		bookingList.add(booking1);

		@SuppressWarnings("unused")
		String jsonInput = this.converttoJson(bookingList);

		Mockito.when(adminService.fetchAllBookings()).thenReturn(bookingList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		//String jsonOutput = mockHttpServletResponse.getContentAsString();

		///assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	/**
	 * testCancelBooking()
	 * testing if booking is cancelled.
	 * @throws Exception
	 */
	@Test
	public void testCancelBooking() throws Exception {
		String URI = "/admin/cancelBooking/{BId}";
		Booking booking = new Booking();
		booking.setBId(14);
		booking.setFromDate(new Date());
		booking.setStatus(0);
		booking.setToDate(new Date());
		booking.setUser(new User());
		booking.setVehicle(new Vehicle());

		String jsonInput = this.converttoJson(booking);


		Mockito.when(adminService.confirmBooking(Mockito.anyInt())).thenReturn(booking);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,12).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testListEnquiry() throws Exception{
		String URI= "/admin/getAllEnquiries";
		Enquiry enquiry = new Enquiry();
		enquiry.setEId(11);
		enquiry.setEmail("alka@gmail.com");
		enquiry.setDetails("Vehicle Type");
		enquiry.setResponse("yes");

		Enquiry enquiry1 = new Enquiry();
		enquiry1.setEId(12);
		enquiry1.setEmail("pooja@gmail.com");
		enquiry1.setDetails("Vehicle Type");
		enquiry1.setResponse("No");

		List<Enquiry> enquiryList = new ArrayList<>();
		enquiryList.add(enquiry);
		enquiryList.add(enquiry1);

		String jsonInput = this.converttoJson(enquiryList);

		Mockito.when(adminService.fetchAllEnquiry()).thenReturn(enquiryList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	/**
	 * testUpdateEnquiry()
	 * testing if enquiry is updated.
	 * @throws Exception
	 */

	@Test
	public void testUpdateEnquiry() throws Exception {
		String URI= "/admin/respondEnquiry/{EId}";
		Enquiry enquiry = new Enquiry();
		enquiry.setEId(40);
		enquiry.setDetails("i need a truck");
		enquiry.setEmail("abc@gmail.com");
		enquiry.setResponse("ok");

		String jsonInput = this.converttoJson(enquiry);


		Mockito.when(adminService.respondEnquiry(Mockito.anyInt(),Mockito.any(Enquiry.class))).thenReturn(enquiry);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,12).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());


	}

	/**
	 * Convert Object into Json String by using Jackson ObjectMapper
	 * @param ticket
	 * @return
	 * @throws JsonProcessingException
	 */
	private String converttoJson(Object vehicle) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(vehicle);
	}
}