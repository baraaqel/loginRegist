package com.loginandrej.demo.Controllers;

import com.loginandrej.demo.models.User;
import com.loginandrej.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class Users {
    private final UserService userService;

    public Users(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    @RequestMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }

    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        if(result.hasErrors()) {
            return "registration.jsp";
        } else {
            userService.registerUser(user);
            session.setAttribute("user_id", user.getId());
            return "redirect:/home";
        }

    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        User user = userService.findByEmail(email);
        if(userService.authenticateUser(email, password)) {
            session.setAttribute("user_id", user.getId());
            return "homePage.jsp";
        } else {
            model.addAttribute("message", "Invalid info");
            return "redirect:/login";
        }
    }

    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        Long user_id = (Long) session.getAttribute("user_id");
        User user = userService.findUserById(user_id);
        return "homePage.jsp";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session.isNew()) {
            return "redirect:/login";
        } else {
            session.invalidate();
            return "redirect:/login";
        }

    }
}