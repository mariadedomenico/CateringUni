package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {


	

	boolean existsByNome(String nome);

	boolean existsByNomeAndDescrizioneAndImg(String nome, String descrizione, String img);

	boolean existsByNomeAndDescrizione(String nome, String descrizione);
	
	

}
