package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Chef;
import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.repository.ChefRepository;

@Service
public class ChefService {

	
	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public void save(Chef chef) {
		this.chefRepository.save(chef);
	}
	
	public Chef findById(Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	public List<Chef> findAll() {
		List<Chef> chefs = new ArrayList<Chef>();
		for(Chef c : chefRepository.findAll()) {
			chefs.add(c);
		}
		return chefs;
	}
	
	public boolean alreadyExists(Chef chef) {
		return this.chefRepository.existsById(chef.getId());
	}

	@Transactional
	public void deleteById(Long id) {
		this.chefRepository.deleteById(id);
		
	}

	public boolean alreadyExistsByNomeAndCognomeAndNazionalita(Chef chef) {
		// TODO Auto-generated method stub
		return this.chefRepository.existsByNomeAndCognomeAndNazionalita(chef.getNome(), chef.getCognome(), chef.getNazionalita());
	}

	public List<Chef> findAllOrderByNomeAsc() {
		return this.chefRepository.findAllByOrderByNomeAsc();
	}
	
	public List<Chef> findAllOrderByNomeDesc() {
		return this.chefRepository.findAllByOrderByNomeDesc();
	}

	
}
