package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Fisioterapia;
import com.example.demo.model.Fisioterapista;
import com.example.demo.repository.FisioterapistaRepository;

@Service
public class FisioterapistaService {

	@Autowired FisioterapistaRepository fisioterapistaRepository;
	
	public List<Fisioterapista> findAll(){
		List<Fisioterapista> fisioterapisti = new LinkedList<Fisioterapista>();
		for(Fisioterapista f : this.fisioterapistaRepository.findAll())
			fisioterapisti.add(f);
		return fisioterapisti;
	}
	
	
	public Fisioterapista findById(Long id) {
		return this.fisioterapistaRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Fisioterapista f) {
		this.fisioterapistaRepository.save(f);
	}
	
	@Transactional
	public void delete(Fisioterapista f) {
		this.fisioterapistaRepository.delete(f);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.fisioterapistaRepository.deleteById(id);
	}

	@Transactional
	public void update(Fisioterapista fisioterapista) {
		// TODO Auto-generated method stub
		Fisioterapista foo = this.fisioterapistaRepository.findById(fisioterapista.getId()).get();
		foo.setName(fisioterapista.getName());
		foo.setSurname(fisioterapista.getSurname());
		this.fisioterapistaRepository.save(foo);
	}

	@Transactional
	public void addFisioterapia(Long idFisioterapista, Fisioterapia fisioterapia) {
		// TODO Auto-generated method stub
		Fisioterapista fisioterapista = this.fisioterapistaRepository.findById(idFisioterapista).get();
		fisioterapista.getFisioterapie().add(fisioterapia);
		fisioterapia.setFisioterapista(fisioterapista);
		this.fisioterapistaRepository.save(fisioterapista);
	}
	
	public List<Fisioterapista> lastInsertedFisioterapista(){
		return this.fisioterapistaRepository.findTop3ByOrderByIdDesc();
	}


	public boolean alreadyExist(Fisioterapista fisioterapista) {
		// TODO Auto-generated method stub
		return this.fisioterapistaRepository.existsByNameAndSurname(fisioterapista.getName(), fisioterapista.getSurname());
	}
}
