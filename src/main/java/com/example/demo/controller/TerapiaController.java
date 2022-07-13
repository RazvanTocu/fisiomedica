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

import com.example.demo.model.Terapia;
import com.example.demo.service.PrestazioneService;
import com.example.demo.service.TerapiaService;

@Controller
@RequestMapping("/terapia")
public class TerapiaController {
	
	public static final String TERAPIA_DIR = "terapia/";

	@Autowired private TerapiaService terapiaService;
	
	@Autowired private PrestazioneService prestazioneService;
	
	private static final String PRESTAZIONE_DIR = "/terapia/";

	@GetMapping("/all")
	public String getTerapiaList(Model model) {
		List<Terapia> terapie = terapiaService.findAll();
		model.addAttribute("terapie",terapie);
		return PRESTAZIONE_DIR + "TerapiaList.html";
	}	
	
	// mostra uno specifica terapia selezionato tramite id
	@GetMapping("/{id}")
	public String getTerapia(@PathVariable("id") Long id, Model model) {
		model.addAttribute("terapia", this.terapiaService.findById(id));
		return "index";
	}	

	
	// mostra la form per l'aggiunta di un nuova terapia per una relativa prestazione
	@GetMapping("/add/{idPrestazione}/{idFisioterapia}")
	public String getAddForm(@PathVariable("idPrestazione") Long idPrestazione, @PathVariable("idFisioterapia") Long idFisioterapia
										,Model model) {
		model.addAttribute("terapia", new Terapia());
		model.addAttribute("prestazione", this.prestazioneService.findById(idPrestazione));
		model.addAttribute("idFisioterapia", idFisioterapia);
		return PRESTAZIONE_DIR + "TerapiaAdd";
	}

	// aggiunge al db una nuova terapia per una relativa prestazione
	@PostMapping("/add/{idPrestazione}/{idFisioterapia}")
	public String addTerapia(@ModelAttribute("terapia") Terapia terapia, @PathVariable("idPrestazione") Long idPrestazione, 
												@PathVariable("idFisioterapia") Long idFisioterapia,Model model) {
		this.prestazioneService.addTerapia(terapia, idPrestazione);
		return "redirect:/fisioterapia/" + idFisioterapia; // voglio ritornare alla pagina che mostra la prestazione
		// redirect mi permette di ripassare per il controller
	}
	
	
	// elimina dal db una terapia selezionato tramite id
	@GetMapping("/delete/{id}/{FisioterapiaId}")
	public String deleteTerapia(@PathVariable("id") Long id,
							@PathVariable("fisioterapiaId") Long fisioterapiaId, Model model) {
		this.terapiaService.deleteById(id);
		return "redirect:/fisioterapia/" + fisioterapiaId;
	}
	
	// mostra la form per l'edit di una terapia selezionato tramite id
	@GetMapping("/edit/{id}/{fisioterapiaId}")
	public String getEditForm(@PathVariable("id") Long id,
										@PathVariable("fisioterapiaId") Long fisioterapiaId, Model model) {
		model.addAttribute("terapia", this.terapiaService.findById(id));
		model.addAttribute("fisioterapiaId", fisioterapiaId);
		return TERAPIA_DIR + "TerapiaEdit";
	}
	
	
	// edita una terapia nel db
	@PostMapping("/edit/{id}/{fisioterapiaId}")
	public String editTerapia(@ModelAttribute("terapia") Terapia terapia, 
								@PathVariable("fisioterapiaId") Long fisioterapiaId, Model model) {
		
		this.terapiaService.update(terapia);
		return "redirect:/fisioterapia/" + fisioterapiaId;
	}	
	
}
