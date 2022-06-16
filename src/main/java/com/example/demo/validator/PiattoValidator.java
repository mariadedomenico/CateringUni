package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Piatto;
import com.example.demo.service.PiattoService;

@Component
public class PiattoValidator implements Validator {

	@Autowired
	private PiattoService piattoService;
	
	@Override
	public boolean supports(Class<?> pClass) {
		
		return Piatto.class.equals(pClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(this.piattoService.alreadyExistByNomeAndDescrizione((Piatto)target)) {
			errors.reject("piatto.duplicato");
		}
		
	}

}
