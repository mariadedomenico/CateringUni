package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		this.ingredienteRepository.save(ingrediente);
	}
	
	public Ingrediente findById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
	
	public List<Ingrediente> findAll() {
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for(Ingrediente i : ingredienteRepository.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}
	public boolean alreadyExists(Ingrediente ingrediente) {
		return this.ingredienteRepository.existsById(ingrediente.getId());
	}

	@Transactional
	public void deleteById(Long id) {
		this.ingredienteRepository.deleteById(id);
		
	}

	public boolean alreadyExistsByNomeAndDescrizioneAndOrigine(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return this.ingredienteRepository.existsByNomeAndDescrizioneAndOrigine(ingrediente.getNome(), ingrediente.getDescrizione(), ingrediente.getOrigine());
	}
	

	
	

}
