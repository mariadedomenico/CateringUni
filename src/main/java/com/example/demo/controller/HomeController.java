package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Utente;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.service.PiattoService;
import com.example.demo.service.UtenteService;

@Controller
public class HomeController {
	
	@Autowired 
	private ChefService chefService;
	@Autowired 
	private BuffetService buffetService;
	@Autowired
	private PiattoService piattoService;
	@Autowired
	private UtenteService utenteService;

	
	
	@GetMapping("/user/home")
	public String getChefs(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
//		model.addAttribute("piatti", this.piattoService.findAll());
		return "home.html";
	}
	
	@GetMapping("/user/contatti") 
	public String getContatti(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		model.addAttribute("utente", new Utente());
		return "contatti.html";
	}
	
	

}
