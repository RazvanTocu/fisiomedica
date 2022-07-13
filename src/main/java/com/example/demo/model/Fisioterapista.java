package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fisioterapista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private int eta;

	@OneToMany(mappedBy = "fisioterapista", cascade = CascadeType.ALL)
	private List<Fisioterapia> fisioterapie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public List<Fisioterapia> getFisioterapie() {
		return fisioterapie;
	}

	public void setFisioterapie(List<Fisioterapia> fisioterapie) {
		this.fisioterapie = fisioterapie;
	}
	
}
