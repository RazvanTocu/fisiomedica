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
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Fisioterapia;
import com.example.demo.service.FisioterapiaService;
import com.example.demo.service.FisioterapistaService;
import com.example.demo.validator.FisioterapiaValidator;

@Controller
@RequestMapping("/fisioterapia")
public class FisioterapiaController {
	
	@Autowired FisioterapiaService   fisioterapiaService;
	@Autowired FisioterapiaValidator fisioterapiaValidator;
	
	@Autowired FisioterapistaService fisioterapistaService;
	
	public static final String FISIOTERAPIA_DIR = "fisioterapia/";
	
	// mostra tutte le fisioterapie nel db
	@GetMapping("/all")
	public String getFisioterapiaList(Model model) {
		List<Fisioterapia> fisioterapie = fisioterapiaService.findAll();
		model.addAttribute("fisioterapie",fisioterapie);
		return FISIOTERAPIA_DIR + "FisioterapiaList.html";
		
	}
	
	// mostra una specifica fisioterapia selezionato tramite id
	@GetMapping("/{id}")
	public String getFisioterapia(@PathVariable("id") Long id, Model model) {
		Fisioterapia fisioterapia = this.fisioterapiaService.findById(id); 
		model.addAttribute("fisioterapia", fisioterapia);
		model.addAttribute("prestazioni", fisioterapia.getPrestazioni());
		return FISIOTERAPIA_DIR + "FisioterapiaProfile";
	}
	
	// mostra la form per l'aggiunta di una nuova fisioterapia
	@GetMapping("/add/{idFisioterapista}")
	public String getAddForm(@PathVariable("idFisioterapista") Long idFisioterapista, Model model) {
		model.addAttribute("fisioterapia", new Fisioterapia());
		model.addAttribute("idFisioterapista",idFisioterapista);
		return FISIOTERAPIA_DIR + "FisioterapiaAdd";
	}

	// aggiunge al db una nuova fisioterapia
	@PostMapping("/add/{idFisioterapista}")
	public String addFisioterapia(@Valid @ModelAttribute("fisioterapia") Fisioterapia fisioterapia,BindingResult bindingResult, 
							@PathVariable("idFisioterapista") Long idFisioterapista,Model model) {
		
	// occhio, il parametro bindingresult va vicino al parametro da validare, 
	// altrimenti non funziona 
		this.fisioterapiaValidator.validate(fisioterapia, bindingResult);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("fisioterapia", fisioterapia);
			model.addAttribute("idFisioterapista",idFisioterapista);			
			return FISIOTERAPIA_DIR + "FisioterapiaAdd";
		}
		else {
			this.fisioterapistaService.addFisioterapia(idFisioterapista,fisioterapia);
			System.out.println("arrivo qua 2");			
			return "redirect:/fisioterapista/" + idFisioterapista;			
		}
	
		

	}
	
	// elimina dal db una fisioterapia selezionato tramite id
	@GetMapping("/delete/{id}")
	public String deleteFisioterapia(@PathVariable("id") Long id,Model model) {
		Long idFisioterapista = this.fisioterapiaService.findById(id).getFisioterapista().getId();
		this.fisioterapiaService.deleteById(id);
		return "redirect:/fisioterapista/" + idFisioterapista;
	}
	
	// mostra la form per l'edit di una fisioterapia selezionato tramite id
	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("fisioterapia", this.fisioterapiaService.findById(id));
		return FISIOTERAPIA_DIR + "FisioterapiaEdit";
	}
	
	
	// edita una fisioterapia nel db
	@PostMapping("/edit/{id}")
	public String editFisioterapia(@Valid @ModelAttribute("fisioterapia") Fisioterapia fisioterapia, BindingResult bindingResult ,Model model) {
		
		if(!bindingResult.hasErrors()) {
			this.fisioterapiaService.update(fisioterapia);			
			return "redirect:/fisioterapia/" + fisioterapia.getId();		
		}
		return FISIOTERAPIA_DIR + "FisioterapiaEdit";

	}
	
}
