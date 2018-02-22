package com.user.registration.utilities;

import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.user.registration.entity.User;

@Component
public class UserMailVerificationLink {
	
	public void userMailLink(User userData, JavaMailSender mailSender, VelocityEngine velocityEngine) throws Exception{
		
		String recipientAddress = userData.getEmailId();
		String firstName = userData.getFirstName();
		String lastName = userData.getLastName();
		String appName = "Spring local User Registration";
		
		DataConceal dataConceal = new DataConceal();
		String encodeData = dataConceal.dataEncode(recipientAddress);
		
		String link="http://192.168.0.108:8080/User_Registration/accountVerification?verify="+encodeData;
		
		System.out.println(link);
		
		MimeMessage mimemsg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimemsg, true);
        
        Template template = velocityEngine.getTemplate("./Mail_Templates/testMail.html");
        VelocityContext velocityContext = new VelocityContext();
        
        velocityContext.put("app_name", appName);
        velocityContext.put("first_name", firstName);
        velocityContext.put("last_name", lastName);
        velocityContext.put("link", link);
        
        StringWriter stringWriter = new StringWriter();
        
        template.merge(velocityContext, stringWriter);
        String subject = (String) velocityContext.get("subject");
        
        
        helper.setTo(recipientAddress);
		helper.setSubject(subject);
        helper.setText(stringWriter.toString(),true);
        
        mailSender.send(mimemsg);
        System.out.println("Mail sent Successfully");
        
	}
	
}

