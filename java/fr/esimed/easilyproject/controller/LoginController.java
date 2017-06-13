package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.enums.Views;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Inject
    private HttpServletRequest request;

    @RequestMapping("/signup")
    public String signup(Model model) {

        return Views.SIGNUP.toString();
    }

    @RequestMapping("/logout")
    public String logout(Model model) {

        try {

            request.logout();

        } catch (Exception ex) {

        }

        return Views.SIGNIN.toString();
    }
}