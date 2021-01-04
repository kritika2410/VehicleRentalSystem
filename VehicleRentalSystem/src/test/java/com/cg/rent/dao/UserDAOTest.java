package com.cg.rent.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.cg.rent.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTest {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	TestEntityManager testEntityManager;
	
		@Test
	    public void testNewVehicle() throws Exception{
	        User user = new User();
	        user.setEmail("test1@gmail.com");
	        user.setName("Test1");
	        user.setAddress("test1 address");
	        user.setContact("5678954367");
	        user.setRole("User");
	        user.setPassword("test123");
	        User saveInDb = testEntityManager.persist(user);
	        User getFromInDb = userDAO.findById(saveInDb.getUId()).get();
	        assertThat(getFromInDb).isEqualTo(saveInDb);
	    }

	

}
