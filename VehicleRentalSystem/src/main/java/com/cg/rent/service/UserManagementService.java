package com.cg.rent.service;

import com.cg.rent.model.Login;
import com.cg.rent.model.User;

/**
 * UserManagementService class
 * This class will handle tasks specific to all users
 * @author 15BW089AX
 *
 */
public interface UserManagementService {

	public void registerUser(User user) ;
	
	public void changePassword(int UId, User user);
	
	public String login(Login login);
	
	public void logout(String email);
}
