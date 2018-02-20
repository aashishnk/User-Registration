package UserUtilities;

import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.user.registration.entity.User;

@Component
public class UserMailVerificationLink {
	
	public void userMailLink(User userData, JavaMailSender mailSender, VelocityEngine velocityEngine) throws ResourceNotFoundException, ParseErrorException, Exception{
		
		String recipientAddress = userData.getEmailId();
		String first_name = userData.getFirstName();
		String last_name = userData.getLastName();
		String app_name = "Spring local User Registration";
		
		DataConceal dataConceal = new DataConceal();
		String encodeData = dataConceal.dataEncode(recipientAddress);
		
		String link="http://192.168.0.108:8080/User_Registration/accountVerification?verify="+encodeData;
		
		System.out.println(link);
		
        //String subject = "Account Mail Verificaion";
        //String message = "Dear user!!\n\nPlease click on link below for confirmation of your account registration.\n\n"+link;
       
		MimeMessage mimemsg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimemsg, true);
        
        Template template = velocityEngine.getTemplate("./Mail_Templates/testMail.html");
        VelocityContext velocityContext = new VelocityContext();
        
        velocityContext.put("app_name", app_name);
        velocityContext.put("first_name", first_name);
        velocityContext.put("last_name", last_name);
        velocityContext.put("link", link);
        
        StringWriter stringWriter = new StringWriter();
        
        template.merge(velocityContext, stringWriter);
        String subject = (String) velocityContext.get("subject");
        
        
        helper.setTo(recipientAddress);
		helper.setSubject(subject);
        helper.setText(stringWriter.toString(),true);
        
        
        
        /*System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);*/
         
        mailSender.send(mimemsg);
        System.out.println("Mail sent Successfully");
        
	}
	
	/*public static void main(String args[]) {
		
		UserMailVerificationLink obj = new UserMailVerificationLink();
		obj.userMailLink("aashishnk@gmail.com");
		
	}*/
	
}

//172.29.36.48 - Office IP
//192.168.0.108 - Home IP