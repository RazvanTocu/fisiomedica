package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Prestazione;
import com.example.demo.service.FisioterapiaService;
import com.example.demo.service.PrestazioneService;

@Controller
@RequestMapping("/prestazione")
public class PrestazioneController {
	
	@Autowired private PrestazioneService prestazioneService;

	@Autowired private FisioterapiaService fisioterapiaService;
	
	private static final String PRESTAZIONE_DIR = "prestazione/";

	@GetMapping("/all")
	public String getPrestazioneList(Model model) {
		List<Prestazione> prestazioni = prestazioneService.findAll();
		model.addAttribute("prestazioni",prestazioni);
		return PRESTAZIONE_DIR + "PrestazioneList.html";
	}	
	
	// mostra una specifica prestazione selezionato tramite id
	@GetMapping("/{id}")
	public String getPrestazione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("prestazione", this.prestazioneService.findById(id));
		return "index";
	}	

	
	// mostra la form per l'aggiunta di una nuova prestazione per una relativa fisioterapia
	@GetMapping("/add/form/{idFisioterapia}")
	public String getAddForm(@PathVariable("idFisioterapia") Long idFisioterapia, Model model) {
		model.addAttribute("prestazione", new Prestazione());
		model.addAttribute("idFisioterapia", idFisioterapia);
		return PRESTAZIONE_DIR + "prestazioneAdd";
	}

	// aggiunge al db una nuova prestazione per una relativa fisioterapia
	@PostMapping("/add/{idFisioterapia}")
	public String addPrestazione(@ModelAttribute("prestazione") Prestazione prestazione, @PathVariable("idFisioterapia") Long idFisioterapia, 
												Model model) {
		this.fisioterapiaService.addPrestazioneNuova(prestazione, idFisioterapia);
		return "redirect:/fisioterapia/" + idFisioterapia; // voglio ritornare alla pagina che mostra la fisioterapia
		// redirect mi permette di ripassare per il controller
	}
	
	
	// elimina dal db un prestazione selezionato tramite id
	@GetMapping("/delete/{id}/{idFisioterapia}")
	public String deletePrestazione(@PathVariable("id") Long id, @PathVariable("idFisioterapia") Long idFisioterapia,
											Model model) {
		this.prestazioneService.deleteById(id);
		model.addAttribute("prestazioni", this.prestazioneService.findAll());
		return "redirect:/fisioterapia/" + idFisioterapia; //ritorno alla pagine del fisioterapia in cui ero
	}
	
	// mostra la form per l'edit di una prestazione selezionato tramite id
	@GetMapping("/edit/{id}/{idFisioterapia}")
	public String getEditForm(@PathVariable("id") Long id, @PathVariable("idFisioterapia") Long idFisioterapia
														,Model model) {
		model.addAttribute("prestazione", this.prestazioneService.findById(id));
		model.addAttribute("idFisioterapia", idFisioterapia);
		return PRESTAZIONE_DIR + "PrestazioneEdit";
	}
	
	
	// edita una prestazione nel db
	@PostMapping("/edit/{id}/{idFisioterapia}")
	public String editPrestazione(@ModelAttribute("prestazione") Prestazione prestazione,@PathVariable("idFisioterapia") Long idFisioterapia,
											Model model) {
		this.prestazioneService.update(prestazione);
		return "redirect:/fisioterapia/" + idFisioterapia;
	}
	
}
