package com.example.demo.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descrizione;
	
	private String img;
	private float media;
	
	@ManyToOne
	private Chef chef;
	
	@ManyToMany
	private List<Piatto> piatti;
	
	public Buffet() {
	}

	public Buffet(String nome, String descrizione, Chef chef, String img, List<Piatto> piatti, float media) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.chef=chef;
		this.img=img;
		this.piatti = piatti;
		this.media=media;
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

	public Chef getChef() {
		return this.chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public List<Piatto> getPiatti() {
		return this.piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}

	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public float getMedia() {
		return media;
		
	}

	public void setMedia(float media) {
		this.media = media;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buffet other = (Buffet) obj;
		return Objects.equals(nome, other.nome);
	}
	
	
	
	
	
}
