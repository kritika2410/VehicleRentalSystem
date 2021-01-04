package com.cg.rent.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.rent.model.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleDAOTest {
	
	@Autowired
	VehicleDAO vehicleDAO;
	
	@Autowired
	TestEntityManager testEntityManager;

	@Test
	public void testNewVehicle() throws Exception{
		Vehicle vehicle = new Vehicle();
		vehicle.setVbrand("Audi");
		vehicle.setVType("Car");
		vehicle.setVNumber("MH 02 456");
		Vehicle saveInDb = testEntityManager.persist(vehicle);
		Vehicle getFromInDb = vehicleDAO.findById(saveInDb.getVId()).get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
	}
}
