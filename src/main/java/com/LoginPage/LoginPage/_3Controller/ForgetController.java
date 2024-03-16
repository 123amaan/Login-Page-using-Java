package com.LoginPage.LoginPage._3Controller;

import com.LoginPage.LoginPage._8EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class ForgetController {
    @Autowired
    private _8EmailService emailService;

    Random random = new Random(1000);

    @RequestMapping("/forgot")
    public String openEmailForm() {
        return "forget_emailForm";
    }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email, HttpSession session) {
        System.out.println("EMAIL : " + email);

        int otp = random.nextInt(9999);
        System.out.println("OTP: " + otp);

        String subject = "OTP from admin";
        String message = "<h1> OTP = " + otp + "</h1>";
        String to = email;

        boolean flag = this.emailService.sendEmail(subject, message, to);
        if (flag) {
            return "verifyOTP";
        } else {
            session.setAttribute("message", "Check your email id !!");
            return "forget_emailForm";

        }

    }

}
