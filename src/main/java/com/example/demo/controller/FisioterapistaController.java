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

import com.example.demo.model.Fisioterapista;
import com.example.demo.service.FisioterapistaService;
import com.example.demo.validator.FisioterapistaValidator;

@Controller
@RequestMapping("/fisioterapista")
public class FisioterapistaController {
	
	@Autowired FisioterapistaService fisioterapistaService;

	@Autowired private FisioterapistaValidator fisioterapistaValidator;
	
	private static final String FISIOTERAPISTA_DIR = "fisioterapista/";

	@GetMapping("/all")
	public String getFisioterapistaList(Model model) {
		List<Fisioterapista> fisioterapisti = fisioterapistaService.findAll();
		model.addAttribute("fisioterapisti",fisioterapisti);
		return FISIOTERAPISTA_DIR + "FisioterapistaList.html";
	}	
	
	// mostra uno specifico fisioterapista selezionato tramite id
	@GetMapping("/{id}")
	public String getFisioterapista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("fisioterapista", this.fisioterapistaService.findById(id));
		return FISIOTERAPISTA_DIR + "FisioterapistaProfile";
	}	

	
	// mostra la form per l'aggiunta di un nuovo fisioterapista
	@GetMapping("/add/form")
	public String getAddForm(Model model) {
		model.addAttribute("fisioterapista", new Fisioterapista());
		return FISIOTERAPISTA_DIR + "fisioterapistaAdd";
	}

	// aggiunge al db un nuovo fisioterapista
	@PostMapping("/add")
	public String addfisioterapista(@Valid @ModelAttribute("fisioterapista") Fisioterapista fisioterapista, BindingResult bindingResult ,Model model) {
		this.fisioterapistaValidator.validate(fisioterapista, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.fisioterapistaService.save(fisioterapista);
			return this.getFisioterapista(fisioterapista.getId(), model); //mostro il riepilogo dello chef inserito			
		}
		return FISIOTERAPISTA_DIR + "fisioterapistaAdd";

	}
	
	// elimina dal db un fisioterapista selezionato tramite id
	@GetMapping("/delete/{id}")
	public String deleteFisioterapista(@PathVariable("id") Long id,Model model) {
		this.fisioterapistaService.deleteById(id);
		return "redirect:/fisioterapista/all";
	}
	
	// mostra la form per l'edit di un fisioterapista selezionato tramite id
	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("fisioterapista", this.fisioterapistaService.findById(id));
		return FISIOTERAPISTA_DIR + "FisioterapistaEdit";
	}
	
	
	// edita un fisioterapista nel db
	@PostMapping("/edit/{id}")
	public String editFisioterapista(@Valid @ModelAttribute("fisioterapista") Fisioterapista fisioterapista, BindingResult bindingResult, Model model) {
		this.fisioterapistaValidator.validate(fisioterapista, bindingResult);	
		if(!bindingResult.hasErrors()) {		
			this.fisioterapistaService.update(fisioterapista);
			return "redirect:/fisioterapista/all";
		}
		return FISIOTERAPISTA_DIR + "FisioterapistaEdit";
	}
	
}
