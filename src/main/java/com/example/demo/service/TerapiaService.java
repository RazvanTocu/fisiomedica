package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Terapia;
import com.example.demo.repository.TerapiaRepository;

@Service
public class TerapiaService {

	@Autowired private TerapiaRepository terapiaRepository;
	
	public List<Terapia> findAll(){
		List<Terapia> terapie = new LinkedList<Terapia>();
		for(Terapia t: this.terapiaRepository.findAll())
			terapie.add(t);
		return terapie;
	}

	public Terapia findById(Long id) {
		return this.terapiaRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Terapia t) {
		this.terapiaRepository.save(t);
	}
	
	@Transactional
	public void delete(Terapia t) {
		this.terapiaRepository.delete(t);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.terapiaRepository.deleteById(id);
	}

	@Transactional
	public void update(Terapia terapia) {
		// TODO Auto-generated method stub
		Terapia foo = this.terapiaRepository.findById(terapia.getId()).get();
		foo.setDate(terapia.getDate());
		foo.setOrario(terapia.getOrario());	
		this.terapiaRepository.save(foo);
	}
}
