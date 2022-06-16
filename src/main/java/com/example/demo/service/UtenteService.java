package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Utente;
import com.example.demo.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;
	
	@Transactional
	public void save(Utente utente) {
		utenteRepository.save(utente);
	}
	
	public Utente findById(Long id) {
		return this.utenteRepository.findById(id).get();
	}
	
	public List<Utente> findAll() {
		List<Utente> utenti = new ArrayList<Utente>();
		for(Utente u : this.utenteRepository.findAll()) {
			utenti.add(u);
		}
		return utenti;
	}

	public boolean alreadyExistsByEmail(Utente utente) {
		return this.utenteRepository.existsByEmail(utente.getEmail());
	}

	public void deleteById(Long id) {
		this.utenteRepository.deleteById(id);
		
	}

	public Utente findByEmail(String email) {
		return utenteRepository.findByEmail(email).get();
	}

	public Utente[] findAllByEmail(String email) {
		return utenteRepository.findAllByEmail(email);
	}

	public List<Utente> findAllOrderByEmailAsc() {
		return this.utenteRepository.findAllByOrderByEmailAsc();
	}
	
	public List<Utente> findAllOrderByEmailDesc() {
		return this.utenteRepository.findAllByOrderByEmailDesc();
	}
	
}
