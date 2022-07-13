package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.FisioterapiaService;
import com.example.demo.service.FisioterapistaService;

@Controller
public class FisiomedicaController {
	
	@Autowired private FisioterapiaService fisioterapiaService;
	@Autowired private FisioterapistaService fisioterapistaService;	
	
	@GetMapping("/")
	public String homepage(Model model) {
		model.addAttribute("fisioterapisti", this.fisioterapistaService.lastInsertedFisioterapista());
		model.addAttribute("fisioterapie", this.fisioterapiaService.lastInsertedFisioterapia());
		return "index";
	}

}
