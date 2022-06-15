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
	private BuffetService buffetService;
	@Autowired
	private ChefService chefService;
	@Autowired
	private PiattoService piattoService;


	@Autowired
	private AmministratoreValidator amministratoreValidator;
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new Amministratore());
		model.addAttribute("credentials", new Credentials());
		return "registerForm.html";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginForm.html";
	}
	
	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index.html";
	}
	
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
    		model.addAttribute("chefsAdmin", this.chefService.findAll());
    		model.addAttribute("piattiAdmin", this.piattoService.findAll());
            return "admin/homeAdmin.html";
        }
        return "index.html";
    }
    
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") Amministratore user, BindingResult userBindingResult, @ModelAttribute("credentials") Credentials credentials,BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.amministratoreValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "loginForm.html";
        }
        return "registerForm.html";
   }
	

}
