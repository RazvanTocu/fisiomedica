package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Fisioterapista;

public interface FisioterapistaRepository extends CrudRepository<Fisioterapista, Long> {
	
	List<Fisioterapista> findTop3ByOrderByIdDesc();

	boolean existsByNameAndSurname(String name, String surname);

}
