package com.user.registration.service;

import java.util.List;

import com.user.registration.entity.User;

public interface UserService {
	
	public void saveNewUser(User theUser);
	
	public List<User> verifyNewUser(String mailId);
	
	public List<User> userLogin(User theUser);
	
	public boolean uniqueUserMailId(String mailId);

}
