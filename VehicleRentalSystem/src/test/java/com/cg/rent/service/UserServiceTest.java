package com.cg.rent.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import com.cg.rent.dao.UserDAO;
import com.cg.rent.dao.VehicleDAO;
import com.cg.rent.exception.ResourceNotFoundException;
import com.cg.rent.model.User;
import com.cg.rent.model.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	UserDAO userDAO;
	
	@MockBean
	VehicleDAO vehicleDAO;
	
	@Autowired
	UserServiceImpl userService;
	
	@Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("test1@gmail.com");
        user.setName("Test1");
        user.setAddress("test1 address");
        user.setContact("5678954367");
        user.setRole("User");
        user.setPassword("test123");

        Mockito.when(userDAO.save(user)).thenReturn(user);
        assertThat(userService.createUser(user)).isEqualTo(user);
    }
	
	@Test
	public void testGetVehicleByBrand() throws ResourceNotFoundException {
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVbrand("Audi");
		vehicle1.setVType("Car");
		vehicle1.setVNumber("MH 02 4506");
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVbrand("Audi");
		vehicle2.setVType("Truck");
		vehicle2.setVNumber("MH 12 5546");
		
		List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);

        Mockito.when(vehicleDAO.findVehicleByBrand("Audi")).thenReturn(vehicleList);
        assertThat(userService.searchVehicleByBrand("Audi")).isEqualTo(vehicleList);
	}
	
	@Test
	public void testGetVehicleByType() throws ResourceNotFoundException {
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVbrand("Audi");
		vehicle1.setVType("Car");
		vehicle1.setVNumber("MH 02 4506");
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVbrand("Audi");
		vehicle2.setVType("Truck");
		vehicle2.setVNumber("MH 12 5546");
		
		List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
       //vehicleList.add(vehicle2);

        Mockito.when(vehicleDAO.findVehicleByType("Car")).thenReturn(vehicleList);
        assertThat(userService.searchVehicleByType("Car")).isEqualTo(vehicleList);
	}
	
	@Test
    public void testUserByEmail(){
        User user = new User();
        user.setEmail("test1@gmail.com");
        user.setName("Test1");
        user.setAddress("test1 address");
        user.setContact("5678954367");
        user.setRole("User");
        user.setPassword("test123");

        Mockito.when(userDAO.findUserByEmail("test1@gmail.com")).thenReturn(user);
        assertThat(userService.findUserByEmail("test1@gmail.com")).isEqualTo(user);
    }
	
}
