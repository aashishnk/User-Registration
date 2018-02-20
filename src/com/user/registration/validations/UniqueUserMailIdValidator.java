package com.user.registration.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.registration.service.UserService;
import com.user.registration.service.UserServiceImpl;


public class UniqueUserMailIdValidator implements ConstraintValidator<UniqueMailId, String>{
	
	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(UniqueMailId mailId) {

	}

	@Override
	public boolean isValid(String mailId, ConstraintValidatorContext theConstraintValidatorContext) {

		if(mailId == null) {
			return true;
		}
		
		System.out.println("Validation Mail Id = "+mailId);
		
		if((userService.uniqueUserMailId(mailId))) {
			
			return false;
		}
		
		return true;
	}

}
