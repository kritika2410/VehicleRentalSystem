package com.cg.rent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.rent.dao.UserDAO;
import com.cg.rent.model.Login;
import com.cg.rent.model.User;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementServiceTest {

	@MockBean
	UserDAO userDAO;
	
	@Autowired
	UserManagementServiceImpl userManager;
	
	@Test
    public void testRegisterUser(){
        User user = new User();
        user.setEmail("test1@gmail.com");
        user.setName("Test1");
        user.setAddress("test1 address");
        user.setContact("5678954367");
        user.setRole("User");
        user.setPassword("test123");

        Mockito.when(userDAO.save(user)).thenReturn(user);
        assertThat(userManager.registerUser(user)).isEqualTo(user);
    }
	
	@Test
	public void testLogin()
	{
		User user = new User();
        user.setEmail("dhanashree@gmail.com");
        user.setName("Dhanashree");
        user.setAddress("test1 address");
        user.setContact("5678954367");
        user.setRole("User");
        user.setPassword("5678");
        Login login = new Login();
        login.setEmail("dhanashree@gmail.com");
        login.setPassword("5678");
        Mockito.when(userDAO.save(user)).thenReturn(user);
        assertEquals("Welcome User Dhanashree\\nYour User Id :7",userManager.login(login));
	}
	
	@Test
	public void testFalseLogin()
	{
		Login login = new Login();
		login.setEmail("test1@gmail.com");
		login.setPassword("test123");

		assertEquals("Please Enter correct Email",userManager.login(login));
	}
	
}
