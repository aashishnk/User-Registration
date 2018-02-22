package com.user.registration.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.registration.service.UserService;


public class UniqueUserMailIdValidator implements ConstraintValidator<UniqueMailId, String>{
	
	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(UniqueMailId mailId) { 
		/*At validation statement, no input is required so this method is empty*/

	}

	@Override
	public boolean isValid(String mailId, ConstraintValidatorContext theConstraintValidatorContext) {

		if(mailId == null) {
			return true;
		}
		
		return userService.uniqueUserMailId(mailId);
		
	}

}
