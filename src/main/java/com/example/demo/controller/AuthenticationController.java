package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Amministratore;
import com.example.demo.model.Credentials;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.service.CredentialsService;
import com.example.demo.service.PiattoService;
import com.example.demo.validator.AmministratoreValidator;
import com.example.demo.validator.CredentialsValidator;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private AmministratoreValidator amministratoreValidator;
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm () {
		return "loginForm.html";
	}
	
	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET) 
	public String logout() {
		return "index.html";
	}
	
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/homeAdmin.html";
        }
        return "index.html";
    }	

}
