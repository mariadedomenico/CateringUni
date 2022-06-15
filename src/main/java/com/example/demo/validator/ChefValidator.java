package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Chef;
import com.example.demo.service.ChefService;

@Component
public class ChefValidator implements Validator {

	@Autowired
	private ChefService chefService;
	
	@Override
	public boolean supports(Class<?> cClass) {
		// TODO Auto-generated method stub
		return Chef.class.equals(cClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		if(this.chefService.alreadyExistsByNomeAndCognomeAndNazionalita((Chef)target)) {
			errors.reject("chef.duplicato");
		}
		
	}

}
