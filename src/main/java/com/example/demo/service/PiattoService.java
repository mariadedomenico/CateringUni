package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Piatto;
import com.example.demo.repository.PiattoRepository;



@Service
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Transactional
	public void save(Piatto piatto) {
		piattoRepository.save(piatto);
	}
	
	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}
	
	public List<Piatto> findAll() {
		List<Piatto> piatti = new ArrayList<Piatto>();
		for(Piatto p : piattoRepository.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}
	public boolean alreadyExists(Piatto piatto) {
		return this.piattoRepository.existsById(piatto.getId());
	}
	

	@Transactional
	public void deleteById(Long id) {
		this.piattoRepository.deleteById(id);
		
	}

	
	public boolean alreadyExistByNomeAndDescrizione(Piatto piatto) {
		
		return this.piattoRepository.existsByNomeAndDescrizione(piatto.getNome(), piatto.getDescrizione());
	}

	public Long countByNomeAndDescrizione(String nome, String descrizione) {
		return this.piattoRepository.countByNomeAndDescrizione(nome, descrizione);
	}
}
