package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Buffet;
import com.example.demo.model.Piatto;
import com.example.demo.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;

	@Transactional
	public void save(Buffet buffet) {
		this.buffetRepository.save(buffet);
	}

	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}

	public List<Buffet> findAll() {
		List<Buffet> buffet = new ArrayList<Buffet>();
		for(Buffet b : this.buffetRepository.findAll()) {
			buffet.add(b);
		}
		return buffet;
	}

	

	@Transactional
	public void deleteById(Long id) {
		this.buffetRepository.deleteById(id);
		
	}

	public boolean alreadyExistsByNomeAndDescrizione(Buffet buffet) {
		return this.buffetRepository.existsByNomeAndDescrizione(buffet.getNome(), buffet.getDescrizione());
	}

	public List<Buffet> findAllOrderByNomeAsc() {
		return this.buffetRepository.findAllByOrderByNomeAsc();
	}
	
	public List<Buffet> findAllOrderByNomeDesc() {
		return this.buffetRepository.findAllByOrderByNomeDesc();
	}
	
}
