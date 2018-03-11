package com.user.registration.utilities;

import com.user.registration.entity.User;

public class UserMessages {
	
	public String userLoginErrorMessage(User user, String enteredPassword) {
		
		String loginErrorMsg = null;
		
		if( user == null) {
			loginErrorMsg="No user account exixts with this email-id. Please enter correct email-id.";			
		}
		else if( ! user.getPassword().equals(enteredPassword) ) {
			loginErrorMsg="Please enter correct password.";
		}
		else if( user.getStatus() == 0 ) {
			loginErrorMsg="Your account is not yet activated."
					+ "<br>Please click on the link sent to your registered email-id for "
					+ "confimation of your account registration.";
		}
		else if( user.getActiveFlag() == 0 && user.getStatus() == 1) {
			loginErrorMsg="Your account is currently inactive.";
		}
		
		return loginErrorMsg;
		
	}

}
