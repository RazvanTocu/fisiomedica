package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




@Entity
public class Fisioterapia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Size(min=1, max=255)
	private String description;
	
	@ManyToOne
	private Fisioterapista fisioterapista;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fisioterapia_id")
	private List<Prestazione> prestazioni;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Fisioterapista getFisioterapista() {
		return fisioterapista;
	}

	public void setFisioterapista(Fisioterapista fisioterapista) {
		this.fisioterapista = fisioterapista;
	}

	public List<Prestazione> getPrestazioni() {
		return prestazioni;
	}

	public void setPrestazioni(List<Prestazione> prestazioni) {
		this.prestazioni = prestazioni;
	}
	
	
}
