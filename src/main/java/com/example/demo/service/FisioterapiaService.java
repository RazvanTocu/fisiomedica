package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Fisioterapia;
import com.example.demo.model.Fisioterapista;
import com.example.demo.model.Prestazione;
import com.example.demo.repository.FisioterapiaRepository;

@Service
public class FisioterapiaService {

	@Autowired FisioterapiaRepository fisioterapiaRepository;
	
	public List<Fisioterapia> findAll(){
		List<Fisioterapia> fisioterapie = new LinkedList<Fisioterapia>();
		for(Fisioterapia f : this.fisioterapiaRepository.findAll())
			fisioterapie.add(f);
		return fisioterapie;
	}
	
	
	public Fisioterapia findById(Long id) {
		return this.fisioterapiaRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Fisioterapia f) {
		this.fisioterapiaRepository.save(f);
	}
	
	@Transactional
	public void delete(Fisioterapia f) {
		this.fisioterapiaRepository.delete(f);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.fisioterapiaRepository.deleteById(id);
	}


	@Transactional
	public void update(Fisioterapia fisioterapia) {
		// TODO Auto-generated method stub
		Fisioterapia foo = this.fisioterapiaRepository.findById(fisioterapia.getId()).get();
		foo.setName(fisioterapia.getName());
		foo.setDescription(fisioterapia.getDescription());
		this.fisioterapiaRepository.save(foo);
	}

	
	@Transactional
	public void addPrestazioneNuova(Prestazione prestazione, Long idFisioterapia) {
		// TODO Auto-generated method stub
		Fisioterapia fisioterapia = this.fisioterapiaRepository.findById(idFisioterapia).get();
		fisioterapia.getPrestazioni().add(prestazione);
		this.fisioterapiaRepository.save(fisioterapia);
	}


	public boolean alreadyExist(Fisioterapia fisioterapia) {
		return this.fisioterapiaRepository.existsByName(fisioterapia.getName());
	}
	
	public List<Fisioterapia> lastInsertedFisioterapia(){
		return this.fisioterapiaRepository.findTop3ByOrderByIdDesc();
	}
		
	public Fisioterapia findByNameAndFisioterapista(String name, Fisioterapista fisioterapista) {
		return this.fisioterapiaRepository.findByNameAndFisioterapista(name, fisioterapista);
	}
	
}
