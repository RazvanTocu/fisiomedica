package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Fisioterapia;
import com.example.demo.model.Fisioterapista;

public interface FisioterapiaRepository extends CrudRepository<Fisioterapia, Long> {

	boolean existsByName(String name);
	
	List<Fisioterapia> findTop3ByOrderByIdDesc();
	
	Fisioterapia findByNameAndFisioterapista(String name, Fisioterapista fisioterapista);

}
