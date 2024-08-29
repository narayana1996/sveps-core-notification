package in.co.sveps.contoller;

import in.co.sveps.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        Map<String, Object> model = new HashMap<>();
        model.put("name", "John Doe");
        model.put("message", "This is a sample message");

        try {
            emailService.sendEmail("saideep1617@gmail.com", "Test Subject", model);
            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
}

