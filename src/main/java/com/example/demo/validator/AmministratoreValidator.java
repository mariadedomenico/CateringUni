package com.example.demo.validator;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Amministratore;

@Component
public class AmministratoreValidator implements Validator {

	final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;

    @Override
    public void validate(Object o, Errors errors) {
        Amministratore amministratore = (Amministratore) o;
        String nome = amministratore.getNome().trim();
        String cognome = amministratore.getCognome().trim();

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nome", "size");

        if (cognome.isEmpty())
            errors.rejectValue("cognome", "required");
        else if (cognome.length() < MIN_NAME_LENGTH || cognome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("cognome", "size");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Amministratore.class.equals(clazz);
    }


}
