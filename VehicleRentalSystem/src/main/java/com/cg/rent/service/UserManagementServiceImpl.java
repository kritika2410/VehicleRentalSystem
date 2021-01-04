package com.cg.rent.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.rent.dao.UserDAO;
import com.cg.rent.model.Login;
import com.cg.rent.model.User;

/**
 * UserManagementService class :
 * This class performs User specific operations 
 * @author 15BW089AX
 *
 */
@Service
public class UserManagementServiceImpl {
	
	@Autowired
	UserDAO userDAO;
	
	/**
	 * registerUser() :
	 * This method accepts user instance and store them in database
	 * @param User instance
	 * @return void 
	 */
	public User registerUser(User user) 
	{
		return userDAO.save(user);
	}
	
	/**
	 * changePassword() :
	 * This method accepts new password and update it in database
	 * @param  int UId User Id
	 * @return void
	 */
	public void changePassword(int UId, User user) 
	{
		Optional<User> userOptional = userDAO.findById(UId);
		User user1 = userOptional.get();
		if(user1!=null)
			userDAO.save(user);
	}
	
	/**
	 * login() :
	 * This method validates password and email from database.
	 * @return String 
	 */
	public String login(Login login)
	{
		String result = "";
		
		User user = userDAO.findUserByEmail(login.getEmail());
		if(user!=null)
		{
			if(user.getPassword().equals(login.getPassword()))
			{
				result = "Welcome "+ user.getName()+"\nYour User Id :"+user.getUId();
				if(user.getRole().equalsIgnoreCase("Admin"))
				{
					//String masterPass = login.getMasterPassword();
					//if(masterPass.equals("AdminPass"))
						result="Welcome Admin "+user.getName();
				}
				//else
					//result = "Sorry! You are not admin";
			}
			else
				result = "Please Enter Correct Password";
		}
		else
			result = "Please Enter correct Email";
		return result; 
	}
	
	public void logout(String email)
	{
		
	}
}
