package com.LoginPage.LoginPage;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class _8EmailService {
    public boolean sendEmail(String subject, String message, String to) {
        boolean f = false;
        String from = "amaanthethird@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        System.out.println("PROPERTIES" + properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("amaanthethird@gmail.com", "ebqf nfyv wumf wvmm");
            }
        });

        session.setDebug(true);
        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);
            System.out.println("Sent success !!");
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
