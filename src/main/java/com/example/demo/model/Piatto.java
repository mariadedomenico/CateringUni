package com.example.demo.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descrizione;
	
	@ManyToMany
	private List<Ingrediente> ingredienti;

	public Piatto() {
		
		this.ingredienti = new LinkedList<Ingrediente>();
	}

	public Piatto(String nome, String descrizione, List<Ingrediente> ingredienti) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Ingrediente> getIngredienti() {
		return this.ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		Piatto other = (Piatto) obj;
		return this.getId().equals(other.getId());
	}
	
	

}
