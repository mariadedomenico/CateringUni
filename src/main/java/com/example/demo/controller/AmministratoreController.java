package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.service.PiattoService;
import com.example.demo.validator.BuffetValidator;

@Controller
public class AmministratoreController {
	
	@Autowired 
	private BuffetService buffetService;
	@Autowired
	private BuffetValidator buffetValidator;
	@Autowired 
	private PiattoService piattoService;
	
	@Autowired 
	private ChefService chefService;
	
	@GetMapping("/admin/login")
	public String getLogin(Model model) {
//		model.addAttribute("buffets", this.buffetService.findAll());
//		model.addAttribute("chefs", this.chefService.findAll());
		return "admin/login.html";
	}
	
	@GetMapping("/admin/homeAdmin")
	public String getHome(Model model) {
		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
		model.addAttribute("chefsAdmin", this.chefService.findAll());
		model.addAttribute("piattiAdmin", this.piattoService.findAll());
		return "admin/homeAdmin.html";
	}

}
