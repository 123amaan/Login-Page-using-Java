package com.LoginPage.LoginPage._3Controller;

import com.LoginPage.LoginPage._1Entitiy.User;
import com.LoginPage.LoginPage._2Repository.UserRepo;
import com.LoginPage.LoginPage._5Message;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    // 4.

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Login Page");
        return "index";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register - Login Page");
        model.addAttribute("user", new User()); // it will fill blanks in registration forms
        return "signup";
    }

    @PostMapping("do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               Model model, HttpSession session) {
        try {
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            System.out.println("USER" + user);

            User result = this.userRepo.save(user);
            model.addAttribute("user", new User());
            session.setAttribute("message", new _5Message("Successfully Registered","alert-success"));
            return "signup";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new _5Message("Something went wrong"+e.getMessage(),"alert-danger"));
            return "signup";
        }
    }

    // 10.

    @GetMapping("/login")
    public String customLogin(Model model){
        model.addAttribute("title", "Login - Login Page");
        return "login";
    }

    @GetMapping("/signin")
    public String logout(Model model){
        model.addAttribute("title", "Login - Login Page");
        return "login";
    }
}
