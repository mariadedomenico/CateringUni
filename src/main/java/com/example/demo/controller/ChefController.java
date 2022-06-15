package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Chef;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.validator.ChefValidator;

@Controller
public class ChefController {
	
	@Autowired 
	private ChefService chefService;
	@Autowired
	private ChefValidator chefValidator;
	@Autowired 
	private BuffetService buffetService;
	
	/*convenzione: usiamo metodi get per op di lettura e metodi post per scrittura
	  abbiamo classi di dominio e associamo path a queste*/

	
	@PostMapping("/admin/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
	    this.chefValidator.validate(chef, bindingResult);

		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			return "redirect:/admin/chefs";
		}
		
		model.addAttribute("chefsAdmin", this.chefService.findAll());
		return "admin/chefForm.html";
	}
	
	@PostMapping("/admin/chef/{id}")
	public String editChef(@Valid @ModelAttribute("chefV") Chef chef, @PathVariable("id") Long id, BindingResult bindingResult, Model model) {
		

		if(true) {
			
			this.chefService.save(chef);
			//model.addAttribute("chef", chef);
			model.addAttribute("chefsAdmin", this.chefService.findAll());
			return "admin/chefsAdmin.html";
		}
		
		return "admin/editChef.html";
	}
	
	@GetMapping("/user/chefs")
	public String getChefs(Model model) {
		model.addAttribute("allChef", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "chefs.html";
	}
	
	@GetMapping("/user/chef/{id}") 
	public String getChef(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("chef", this.chefService.findById(id));
	    model.addAttribute("allChef", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
	    return "chef.html";
	}
	
	@GetMapping("/admin/chef/{id}") 
	public String getChefAdmin(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("chef", this.chefService.findById(id));
	    
	    return "admin/chefAdmin.html";
	}
	
	@GetMapping("/admin/chefs")
	public String getChefsAdmin(Model model) {
		model.addAttribute("chefsAdmin", this.chefService.findAll());
		return "admin/chefsAdmin.html";
	}
	
	@GetMapping("/admin/editChef/{id}") 
	public String editBuffetAdmin(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chefV", this.chefService.findById(id));
//		model.addAttribute("chef", new Chef());
		model.addAttribute("chefsAdmin", this.chefService.findAll());
	    //model.addAttribute("buffets", this.buffetService.findAll());
	    return "admin/editChef.html";
	}
	
	@GetMapping("/admin/chefForm") 
	public String getChef(Model model) {
	    model.addAttribute("chef", new Chef());
	    model.addAttribute("chefsAdmin", this.chefService.findAll());
	    return "admin/chefForm.html";
	}
	
	@GetMapping("/admin/chefDelete/{id}") 
	public String toDeleteChef(@PathVariable("id") Long id, Model model) {
	    this.chefService.deleteById(id);
	    model.addAttribute("chefsAdmin", this.chefService.findAll());
	    return "admin/chefsAdmin.html";
	}

}
