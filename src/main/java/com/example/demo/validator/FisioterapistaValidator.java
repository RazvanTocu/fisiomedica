package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Fisioterapista;
import com.example.demo.service.FisioterapistaService;

@Component
public class FisioterapistaValidator implements Validator {

	@Autowired private FisioterapistaService fisioterapistaService;

	@Override
	public boolean supports(Class<?> clazz) {
		Fisioterapista.class.equals(clazz);
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		if(this.fisioterapistaService.alreadyExist((Fisioterapista)target))
			errors.reject("fisioterapista.duplicato");

	}

}
