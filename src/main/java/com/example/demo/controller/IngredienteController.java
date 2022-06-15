package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PiattoService;
import com.example.demo.validator.IngredienteValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredienteController {
	
	@Autowired 
	private IngredienteService ingredienteService;
	@Autowired 
	private PiattoService piattoService;
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	
	@PostMapping("/admin/ingrediente")
	public String addPersona(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.ingredienteService.save(ingrediente);
			
			return "redirect:/admin/ingredienti";
		}
		model.addAttribute("ingredientiAdmin", this.ingredienteService.findAll());
		return "admin/ingredienteForm.html";
	}
	
	@PostMapping("/admin/ingrediente/{id}")
	public String editBuffet(@Valid @ModelAttribute("ingredienteV") Ingrediente ingrediente, @PathVariable("id") Long id, BindingResult bindingResult, Model model) {
		
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			
			this.ingredienteService.save(ingrediente);
			
			return "redirect:/admin/ingredienti";
		}
		model.addAttribute("ingredientiAdmin", this.ingredienteService.findAll());
		return "admin/editIngrediente.html";
	}
	
	
	@GetMapping("/user/ingredienti")
	public String getIngredienti(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "ingredienti.html";
	}
	
	@GetMapping("/user/ingrediente/{id}") 
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("ingrediente", this.ingredienteService.findById(id));
	    return "ingrediente.html";
	}
	
	@GetMapping("/admin/ingrediente/{id}") 
	public String getIngredienteAdmin(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("ingrediente", this.ingredienteService.findById(id));
	    return "admin/ingredienteAdmin.html";
	}
	
	@GetMapping("/admin/ingredienti")
	public String getIngredientiAdmin(Model model) {
		model.addAttribute("ingredientiAdmin", this.ingredienteService.findAll());
		return "admin/ingredientiAdmin.html";
	}
	
	@GetMapping("/admin/editIngrediente/{id}") 
	public String editIngredienteAdmin(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingredienteV", this.ingredienteService.findById(id));
		model.addAttribute("ingredientiAdmin", this.ingredienteService.findAll());
//		model.addAttribute("ingrediente", new Ingrediente());
	    return "admin/editIngrediente.html";
	}
	
	@GetMapping("/admin/ingredienteForm") 
	public String getIngrediente(Model model) {
	    model.addAttribute("ingrediente", new Ingrediente());
	    model.addAttribute("ingredientiAdmin", this.ingredienteService.findAll());
	    return "admin/ingredienteForm.html";
	}
	
	
	@GetMapping("/admin/ingredienteDelete/{id}")
    public String deleteIngrediente(@PathVariable Long id, Model model) {
        boolean presenteInPiatto = false;
        Ingrediente ingrediente = this.ingredienteService.findById(id);
        for(Piatto p : this.piattoService.findAll()) {
            if(p.getIngredienti().contains(ingrediente)) {
                presenteInPiatto = true;
                break;
            }
        }

        model.addAttribute("ingr", ingrediente);

        if(!presenteInPiatto) {
            this.ingredienteService.deleteById(id);
            return "redirect:/admin/ingredienti";
        }

        else {
            List<Ingrediente> ingredienti = this.ingredienteService.findAll();
            model.addAttribute("ingredientiAdmin", ingredienti);
            return "admin/ingredientiAdmin.html";

        }

    }
	

}
