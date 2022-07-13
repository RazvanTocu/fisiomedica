package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Fisioterapia;
import com.example.demo.service.FisioterapiaService;

@Component
public class FisioterapiaValidator implements Validator {

	@Autowired private FisioterapiaService fisioterapiaService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Fisioterapia.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		if(this.fisioterapiaService.alreadyExist((Fisioterapia)target)) {
				errors.reject("fisioterapia.duplicato");
		}

	}

}
