package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyClass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@NotBlank
	@NotNull
	private String email;
	
	private String messaggio;
	
	private int voto;
	
	@ElementCollection(targetClass = Integer.class)
	@MapKeyClass(Long.class)
	private Map<Long, Integer> voti;
	
	//@Column(nullable = true)
	private boolean risposto;

	public Utente() {
		this.risposto=false;
		this.voti=new HashMap<Long, Integer>();
	}

	public Utente(String nome, String email, String messaggio, int voto) {
		this.nome = nome;
		this.email = email;
		this.messaggio = messaggio;
		this.voto=voto;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
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
		Utente other = (Utente) obj;
		return this.getId().equals(other.getId());
	}

	public boolean isRisposto() {
		return risposto;
	}

	public void setRisposto(boolean risposto) {
		this.risposto = risposto;
	}

	public int getVoto() {
		return voto;
		
	}

	public void setVoto(int voto) {
		this.voto = voto;
		
	}

	public Map<Long, Integer> getVoti() {
		return voti;
		
	}

	public void setVoti(Map<Long, Integer> voti) {
		this.voti = voti;
		
	}
}
