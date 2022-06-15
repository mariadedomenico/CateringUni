package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Buffet;
import com.example.demo.service.BuffetService;

@Component
public class BuffetValidator implements Validator {

	@Autowired
	private BuffetService buffetService;
	
	@Override
	public boolean supports(Class<?> bClass) {
		// TODO Auto-generated method stub
		return Buffet.class.equals(bClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		if(this.buffetService.alreadyExistsByNome((Buffet)target)) {
			errors.reject("buffet.duplicato");
		}
		
	}

}
