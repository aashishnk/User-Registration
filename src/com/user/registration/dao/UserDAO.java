package com.user.registration.dao;

import java.util.List;

import com.user.registration.entity.User;

public interface UserDAO {
	
	public void saveNewUser(User theUser);
	
	public List<User> getUserData(String mailId);
	
	public void updateUserStatus(String mailId, int activeFlag, int status);

}
