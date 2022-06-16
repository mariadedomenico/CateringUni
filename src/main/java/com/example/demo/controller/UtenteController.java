package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Utente;
import com.example.demo.service.UtenteService;
import com.example.demo.validator.UtenteValidator;

@Controller
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private UtenteValidator utenteValidator;
	
	@PostMapping("/user/utente")
	public String addUtente(@ModelAttribute("utente") Utente utente, Model model) {
	    
		utenteService.save(utente);
		model.addAttribute("utente", utente);
		return "redirect:/user/contatti";

	}
	
	@GetMapping("/admin/utenteDelete/{id}") 
	public String toDeleteUtente(@PathVariable("id") Long id, Model model) {
	    this.utenteService.deleteById(id);
	    model.addAttribute("utenti", this.utenteService.findAll());
	    return "admin/utentiAdmin.html";
	}
	
	@GetMapping("/admin/rispondi/{id}") 
	public String toReplyUtente(@PathVariable("id") Long id, Model model) {
		this.utenteService.findById(id).setRisposto(true);
		this.utenteService.save(this.utenteService.findById(id));
		model.addAttribute("risposto", true);
	    model.addAttribute("utenti", this.utenteService.findAll());
	    return "admin/utentiAdmin.html";
	}
	
	@GetMapping("/admin/utenti") 
	public String getUtenti(Model model) {
		model.addAttribute("utenti", this.utenteService.findAll());
		return "admin/utentiAdmin.html";
	}
	
	@GetMapping("/admin/utenteDetails/{id}") 
	public String getDetailUtente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("utente", this.utenteService.findById(id));
	    model.addAttribute("utenti", this.utenteService.findAll());
	    return "admin/dettagliUtente.html";
	}
	
	
	@PostMapping("/user/votazione/{id}")
	public String votazione(@PathVariable("id") Long id, @ModelAttribute("utente") Utente utente, Model model, BindingResult bindingResult) {
		
		
		boolean votato=false;
		for(Utente u : this.utenteService.findAllByEmail(utente.getEmail())) {
			if(u.getVoti().containsKey(id)) {
				votato=true;
				break;
			}
		}
		
		if(votato==false) {
			utente.getVoti().put(id, utente.getVoto());
			this.utenteService.save(utente);
		}
		
		return "redirect:/user/buffet/{id}";

	}
	
	@GetMapping("/admin/sortUtente")
	public String sortUtente(Model model){
		
		model.addAttribute("utenti", this.utenteService.findAllOrderByEmailAsc());
		return "admin/utentiAdmin.html";
	}
	
	@GetMapping("/admin/reverseUtente")
	public String reverseUtente(Model model){
	
		model.addAttribute("utenti", this.utenteService.findAllOrderByEmailDesc());
		
		return "admin/utentiAdmin.html";
	}
	
	

}
