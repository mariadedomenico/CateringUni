package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	Long countByNomeAndDescrizione(String nome, String descrizione);
	boolean existsByNomeAndDescrizione(String nome, String descrizione);
	List<Piatto> findAllByOrderByNomeAsc();
	List<Piatto> findAllByOrderByNomeDesc();
	
	

}
