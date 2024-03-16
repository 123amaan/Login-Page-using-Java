package com.LoginPage.LoginPage._3Controller;

import com.LoginPage.LoginPage._1Entitiy.User;
import com.LoginPage.LoginPage._2Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    // 5.

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        // 11.

        String username = principal.getName();
        System.out.println("USERNAME: " + username);
        User user = userRepo.getUserByEmail(username);
        System.out.println("User " + user);

        model.addAttribute("user", user);
        return "normal/user_dashboard";
    }
}
