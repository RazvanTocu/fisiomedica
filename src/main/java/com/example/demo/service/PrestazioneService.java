package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Prestazione;
import com.example.demo.model.Terapia;
import com.example.demo.repository.PrestazioneRepository;

@Service
public class PrestazioneService {
	
	@Autowired private PrestazioneRepository prestazioneRepository;

	public List<Prestazione> findAll(){
		List<Prestazione> prestazioni = new LinkedList<Prestazione>();
		for(Prestazione f : this.prestazioneRepository.findAll())
			prestazioni.add(f);
		return prestazioni;
	}

	public Prestazione findById(Long id) {
		return this.prestazioneRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Prestazione f) {
		this.prestazioneRepository.save(f);
	}
	
	@Transactional
	public void delete(Prestazione f) {
		this.prestazioneRepository.delete(f);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.prestazioneRepository.deleteById(id);
	}


	public void update(Prestazione prestazione) {
		// TODO Auto-generated method stub
		Prestazione foo = this.prestazioneRepository.findById(prestazione.getId()).get();
		foo.setName(prestazione.getName());
		foo.setDescription(prestazione.getDescription());
		this.prestazioneRepository.save(foo);
		
	}

	public void addTerapia(Terapia terapia, Long idPrestazione) {
		// TODO Auto-generated method stub
		Prestazione prestazione = this.prestazioneRepository.findById(idPrestazione).get();
		prestazione.getTerapie().add(terapia);
		this.save(prestazione);
	}	
	
}
