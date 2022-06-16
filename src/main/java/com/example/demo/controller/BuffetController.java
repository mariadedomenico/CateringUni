package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;
import com.example.demo.model.Piatto;
import com.example.demo.model.Utente;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.service.PiattoService;
import com.example.demo.service.UtenteService;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.validator.BuffetValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BuffetController {
	
	@Autowired 
	private BuffetService buffetService;
	@Autowired 
	private UtenteService utenteService;
	@Autowired 
	private PiattoService piattoService;
	@Autowired 
	private ChefService chefService;
	@Autowired
	private BuffetValidator buffetValidator;
	
	
	@PostMapping("/admin/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
	    
		this.buffetValidator.validate(buffet, bindingResult);

		if(!bindingResult.hasErrors()) {
		
			if(multipartFile.getOriginalFilename() != null) {

	        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	            buffet.setImg("/images/" + fileName);
	            this.buffetService.save(buffet);
	            String uploadDir = "src/main/resources/static/";
	            if(fileName != null && multipartFile != null && !fileName.isEmpty())
	            	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        }
			
			return "redirect:/admin/buffets";
		}
		
		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());
	    model.addAttribute("piatti", this.piattoService.findAll());

		return "admin/buffetForm.html";
	}
	
	@PostMapping("/admin/buffet/{id}")
	public String editBuffet(@Valid @ModelAttribute("buffetV") Buffet buffet, @PathVariable("id") Long id, BindingResult bindingResult, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		this.buffetValidator.validate(buffet, bindingResult);
		Buffet buffetVecchio = this.buffetService.findById(id);

		boolean verificato = false;

		if(buffet.getPiatti().size() == 0) {
			verificato = buffetVecchio.getPiatti().size() > 0;
		}

		else if(buffet.getPiatti().size()==buffetVecchio.getPiatti().size()) {
			for(Piatto piatto : buffet.getPiatti()) {
				
				if(!buffetVecchio.getPiatti().contains(piatto)) {
					verificato=true;
					break; 
				}
			}
		}

		else 
			verificato=true;

		if(verificato || !(buffet.getChef().equals(buffetVecchio.getChef())) || !bindingResult.hasErrors()) {
			
			if(multipartFile.getOriginalFilename() != null) {
	        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        	if(fileName != null && multipartFile != null && !fileName.isEmpty()) {
		            buffet.setImg("/images/" + fileName);
		            this.buffetService.save(buffet);
		            String uploadDir = "src/main/resources/static/images/";
		            if(fileName != null && multipartFile != null && !fileName.isEmpty())
		            	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        	}
	        	else {
	        		buffet.setImg(buffetVecchio.getImg());
	        	}
			}
			
			this.buffetService.save(buffet);
			return "redirect:/admin/buffets";
		}
		
		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());
	    model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("buffetV", buffetVecchio);


		return "admin/editBuffet.html";
	}
	
	
	@GetMapping("/user/buffets")
	public String getBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());
		return "buffets.html";
	}
	
	@GetMapping("/user/buffet/{id}") 
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
	    
		model.addAttribute("buffets", this.buffetService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("utente", new Utente());
		float somma = 0;
		float media = 0;
		float conta = 0;
		for(Utente u :  this.utenteService.findAll()) {
			if(u.getVoti().containsKey(id)) {
				somma = somma + u.getVoti().get(id);
				conta++;
			}
		}
		
		if(conta!=0) {
			media=somma/conta;
			buffet.setMedia(media);
			this.buffetService.save(buffet);
			model.addAttribute("media", media);
			model.addAttribute("buffet", buffet);
			model.addAttribute("assente", false);
		}
		else {
			model.addAttribute("assente", true);
			model.addAttribute("buffet", buffet);
		}
		
		
	    return "buffetSingle.html";
	}
	
	@GetMapping("/admin/buffetAdmin/{id}")
	public String getBuffetAdmin(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
		return "admin/buffetAdmin.html";
	}
	
	@GetMapping("/admin/buffets")
	public String getBuffetsAdmin(Model model) {
		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
		return "admin/buffetsAdmin.html";
	}
	
	@GetMapping("/admin/editBuffet/{id}") 
	public String editBuffetAdmin(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffetV", this.buffetService.findById(id));
		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());
	    model.addAttribute("piatti", this.piattoService.findAll());
	    return "admin/editBuffet.html";
	}
	
	@GetMapping("/admin/buffetForm") 
	public String addBuffet(Model model) {
		model.addAttribute("buffetsAdmin", this.buffetService.findAll());
		model.addAttribute("buffet", new Buffet());
	    model.addAttribute("chefs", this.chefService.findAll());
	    model.addAttribute("piatti", this.piattoService.findAll());

	    return "admin/buffetForm.html";
	}
	
	@GetMapping("/admin/buffetDelete/{id}") 
	public String toDeleteBuffet(@PathVariable("id") Long id, Model model) {
	    
		this.buffetService.deleteById(id);
	    model.addAttribute("buffetsAdmin", this.buffetService.findAll());

	    return "redirect:/admin/buffets";
	}

	@GetMapping("/admin/sort")
	public String sortBuffet(Model model){
		List<Buffet> buffets = this.buffetService.findAllOrderByNomeAsc();
		model.addAttribute("buffetsAdmin", buffets);
		return "admin/buffetsAdmin.html";
	}
	
	@GetMapping("/admin/reverse")
	public String reverseBuffet(Model model){
		List<Buffet> buffets = this.buffetService.findAllOrderByNomeDesc();
		model.addAttribute("buffetsAdmin", buffets);
		return "admin/buffetsAdmin.html";
	}

}
