package com.user.registration.aspect;

import java.util.List;

import org.apache.velocity.app.VelocityEngine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.user.registration.entity.User;
import com.user.registration.service.UserService;
import com.user.registration.utilities.UserMailVerificationLink;


@Aspect
@Component
public class UserAspectJ {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	
	@After("execution(* com.user.registration.dao.UserDAOImpl.saveNewUser(..))")
	public void aspectTrial(JoinPoint jp) throws Exception {
		
		MethodSignature ms = (MethodSignature) jp.getSignature();
		
		System.out.println("========>Executing @After Advice for:");
		System.out.println("========>Method : "+ms);
		
		Object[] userInput = jp.getArgs();
		
		for(Object obj:userInput) {
			User userData = (User) obj;
		
			UserMailVerificationLink accntMailVerify = new UserMailVerificationLink();
			accntMailVerify.userMailLink(userData, mailSender,velocityEngine);
			
		}
	}
	
	@AfterReturning(pointcut=
			"execution(* com.user.registration.service.UserServiceImpl.userLogin(..))",
			returning="userLogin")
	public void aspectTrial2(JoinPoint jp, List<User> userLogin) {
		
		MethodSignature ms = (MethodSignature) jp.getSignature();
		
		System.out.println("========>Executing @AfterReturning Advice for:");
		System.out.println("========>Method : "+ms);
		
		Object[] userInput = jp.getArgs();
		
		for(Object obj:userInput) {
			User userData = (User) obj;
		
			if( ! userLogin.isEmpty() )
			{
				if( userLogin.get(0).getPassword().equals(userData.getPassword()) && userLogin.get(0).getStatus() == 1) {
					System.out.println("User "+userLogin.get(0).getFirstName()+" has successfully logined.");
				}
				else{
					System.out.println("User "+userLogin.get(0).getFirstName()+" has entered wrong password.");
				}
			}
		}
	}

}
