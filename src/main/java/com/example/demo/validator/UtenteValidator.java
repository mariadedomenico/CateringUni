package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Piatto;
import com.example.demo.model.Utente;
import com.example.demo.service.UtenteService;

@Component
public class UtenteValidator implements Validator {

	@Autowired
	private UtenteService utenteService;
	
	@Override
	public boolean supports(Class<?> uClass) {
		
		return Piatto.class.equals(uClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(this.utenteService.alreadyExistsByEmail((Utente)target)) {
			errors.reject("utente.duplicato");
		}
		
	}
}
