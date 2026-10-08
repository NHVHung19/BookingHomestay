package com.booking.homestay.shared.service;

import com.booking.homestay.EmailUtil;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
@AllArgsConstructor
public class MailService {

    public void sendMail(NotificationEmail notificationEmail) {
        // dùng gửi mail để gửi tới gmail nhập trong dữ liệu để xác nhận

        final String fromEmail = "nguyenhuuviethung223@gmail.com"; //requires valid gmail id
        final String password = "zyxdcsapkqeytsdc"; // correct password for gmail id
        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        try {
            EmailUtil.sendEmail(session, fromEmail, notificationEmail.getRecipient(),
                    notificationEmail.getSubject(), notificationEmail.getBody());
            System.out.println("Activation email sent!!");
        } catch (Exception e) {
            System.out.printf("Exception occurred when sending mail", e);
            throw new SpringException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }

    }
}
