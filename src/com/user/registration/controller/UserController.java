package com.user.registration.controller;

import java.util.List;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.registration.entity.User;
import com.user.registration.service.UserService;
import com.user.registration.utilities.DataConceal;
import com.user.registration.utilities.UserMailVerificationLink;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	String errorConfirmationLinkPage ="errorConfirmationLinkPage";
	String homescreen ="home-screen";
	String errorMessage = "errorMessage";
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/Homepage")
	public String homeScreen(@ModelAttribute("user") User theUser) {
		
		return homescreen;
	}
	
	@PostMapping("/userLogin")
	public String userLoginPage(@Validated(User.UserLoginPage.class) @ModelAttribute("user") User theUser, BindingResult theBindingResult, Model theModell) {
				
		if (theBindingResult.hasErrors()) {
			return homescreen;
		}
		
		
		if(! userService.userLogin(theUser.getEmailId(), theUser.getPassword())) {
			theModell.addAttribute("loginErrorMessage", "Wrong EmailId/Password combination");
			return homescreen;
		}
		
		return "login-page";
	}
	
	@RequestMapping("/userRegistration")
	public String userRegistrationPage(@ModelAttribute("user") User theUser) {
		
		
		return "user-registration";
	}
	
	@RequestMapping("/RegisterUser")
	public String saveNewUser(@Validated(User.UserRegistrationPage.class) @ModelAttribute("user") User theUser, BindingResult theBindingResult) throws Exception {
		
		if (theBindingResult.hasErrors()) {
			return "user-registration";
		}
		
		UserMailVerificationLink accntMailVerify = new UserMailVerificationLink();
		accntMailVerify.userMailLink(theUser, mailSender,velocityEngine);
		
		userService.saveNewUser(theUser);
		
		return "registrationConfirmationPage";
	}
	
	@GetMapping("/accountVerification")
	public String newUserVerification(@RequestParam(value="verify", required = false) String mailData, Model theModel){
		
		
		if(mailData == null){
			theModel.addAttribute(errorMessage, "You have used error/bad link for conforming your account.");
			return errorConfirmationLinkPage;
		}
		
		DataConceal dataDecode = new DataConceal();
		List<User> userData = userService.verifyNewUser(dataDecode.dataDecode(mailData));
		
		if( userData.isEmpty() ) { 
			
			//User has used bad or wrong link.
			theModel.addAttribute(errorMessage, "You have used error/bad link for conforming your account.");
			
			return errorConfirmationLinkPage;
		}
		else if(/*userData.get(0).getActiveFlag() == 1 && */userData.get(0).getStatus() == 1) {
			
			//User is already activated
			theModel.addAttribute(errorMessage, userData.get(0).getFirstName()+
					", you have already confirmed your account on ");
			theModel.addAttribute("confirmedTime",userData.get(0).getUpdatedDate());
			
			return errorConfirmationLinkPage;
		}
		
		theModel.addAttribute("userData", userData.get(0).getFirstName());
		return "accountConfirmationPage";
	}


}
