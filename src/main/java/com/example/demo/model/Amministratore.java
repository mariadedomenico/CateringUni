package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amministratore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	
	
	
	
	public Amministratore() {
	}
	
	
	public Amministratore(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Amministratore other = (Amministratore) obj;
		return this.getId().equals(other.getId());
	}


	

}
