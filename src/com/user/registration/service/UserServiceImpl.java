package com.user.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.registration.dao.UserDAO;
import com.user.registration.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Override
	@Transactional
	public void saveNewUser(User theUser) {
		
		userDao.saveNewUser(theUser);

	}

	@Override
	@Transactional
	public List<User> verifyNewUser(String mailId) {

		List<User> newUserList = userDao.getUserData(mailId);
		
		if (newUserList.size()==1 && newUserList.get(0).getActiveFlag() == 0 && newUserList.get(0).getStatus() == 0) {
			//Activate the user & display the link to login page
			userDao.updateUserStatus(mailId, 1, 1);
			}
		
	return newUserList;
	}

	@Override
	@Transactional
	public boolean userLogin(String mailId, String password) {
		
		List<User> userData = userDao.getUserData(mailId);
		if(userData.size() == 1 && userData.get(0).getPassword().equals(password) && userData.get(0).getStatus() == 1) {
				
			return true;
		}
		
	return false;	
	}

	@Override
	@Transactional
	public boolean uniqueUserMailId(String mailId) {
		
		List<User> userData = userDao.getUserData(mailId);
		
		return userData.isEmpty();
	}

}
