package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Buffet;
import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.model.Utente;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PiattoService;
import com.example.demo.validator.BuffetValidator;
import com.example.demo.validator.PiattoValidator;

@Controller
public class PiattoController {

	@Autowired 
	private BuffetService buffetService;
	@Autowired 
	private IngredienteService ingredienteService;
	@Autowired
	private BuffetValidator buffetValidator;
	@Autowired 
	private PiattoService piattoService;

	@Autowired 
	private ChefService chefService;
	@Autowired
	private PiattoValidator piattoValidator;


	@PostMapping("/admin/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(piatto, bindingResult);

		if(!bindingResult.hasErrors()) {
			this.piattoService.save(piatto);

			return "redirect:/admin/piatti";
		}

		model.addAttribute("piattiAdmin", this.piattoService.findAll());
		return "admin/piattoForm.html";
	}

	@PostMapping("/admin/piatto/{id}")
	public String editBuffet(@Valid @ModelAttribute("piattoV") Piatto piatto, @PathVariable("id") Long id, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(piatto, bindingResult);
		Piatto piattoVecchio = this.piattoService.findById(id);
		
		boolean verificato = false;
		System.out.println("GUARDAAAAAAAAAAA" + piatto.getIngredienti().size());
		if(piatto.getIngredienti().size() == 0) {
			verificato = piattoVecchio.getIngredienti().size() > 0;
		}
		else if(piatto.getIngredienti().size()==piattoVecchio.getIngredienti().size()) {
			for(Ingrediente ingrediente : piatto.getIngredienti()) {
				System.out.println(ingrediente.getNome());
				if(!piattoVecchio.getIngredienti().contains(ingrediente)) {
					verificato=true;
					break; 
				}
			}
		}
		else 
			verificato=true;


		if(verificato || !bindingResult.hasErrors()) {

			this.piattoService.save(piatto);
			System.out.println("GUARDAAAAAAAAAAA" + this.piattoService.findById(id).getIngredienti().size());
			return "redirect:/admin/piatti";
		}

		model.addAttribute("piattiAdmin", this.piattoService.findAll());
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		
		return "admin/editPiatto.html";
	}


	@GetMapping("/user/piatti")
	public String getPiatti(Model model) {
		model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());

		return "piatti.html";
	}

	@GetMapping("/user/piatto/{id}/{buffet}") 
	public String getPiatto(@PathVariable("id") Long id, @PathVariable("buffet") Long buffet, Model model) {
		Buffet buffetSingle = this.buffetService.findById(buffet);
		model.addAttribute("piattoSelezionato", this.piattoService.findById(id));
		model.addAttribute("buffet", buffetSingle);
		model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("utente", new Utente());
		if(buffetSingle.getMedia()!=0) 
			model.addAttribute("assente", false);
		else
			model.addAttribute("assente", true);
		
		return "piatto.html";
	}


	@GetMapping("/admin/piatto/{id}") 
	public String getPiattoAdmin(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		model.addAttribute("piattiAdmin", this.piattoService.findAll());

		return "admin/piattoAdmin.html";
	}


	@GetMapping("/admin/piatti")
	public String getPiattiAdmin(Model model) {
		model.addAttribute("piattiAdmin", this.piattoService.findAll());
		return "admin/piattiAdmin.html";
	}

	@GetMapping("/admin/editPiatto/{id}") 
	public String editPiattoAdmin(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piattoV", this.piattoService.findById(id));
		model.addAttribute("piattiAdmin", this.piattoService.findAll());
		//model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "admin/editPiatto.html";
	}

	@GetMapping("/admin/piattoForm") 
	public String getPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("piattiAdmin", this.piattoService.findAll());
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "admin/piattoForm.html";
	}

	@GetMapping("/admin/piattoDelete/{id}") 
	public String toDeletePiatto(@PathVariable("id") Long id, Model model) {
		 boolean presenteInBuffet = false;
	        Piatto piatto = this.piattoService.findById(id);
	        for(Buffet b : this.buffetService.findAll()) {
	            if(b.getPiatti().contains(piatto)) {
	                presenteInBuffet = true;
	                break;
	            }
	        }

	        model.addAttribute("piat", piatto);

	        if(!presenteInBuffet) {
	            this.piattoService.deleteById(id);
	            return "redirect:/admin/piatti";
	        }

	        else {
	            List<Piatto> piatti = this.piattoService.findAll();
	            model.addAttribute("piattiAdmin", piatti);
	            return "admin/piattiAdmin.html";

	        }

	}

}
