package in.co.sveps.service;

import java.io.IOException;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;



@Service
public class ASPEmailService  {
	


    @Autowired
    private JavaMailSender emailSender;


    public void sendSimpleMessage(String to, String subject, String text, String[] params) {
    	sendSimpleMessage(new String[] {to},subject,text,params);
    }
    

    public void sendSimpleMessage(String[] to, String subject, String text, String[] params) {
    	if(params!=null && params.length>0) {
    	text = java.text.MessageFormat.format(text,params);
    	}
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("saideep1617@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

   /* @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String ...templateModel) {
        String text = String.format(template.getText(), templateModel);  
        sendSimpleMessage(to, subject, text);
    }*/

 
   
   
}
