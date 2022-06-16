package com.example.demo.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	boolean existsByNomeAndDescrizione(String nome, String descrizione);
	
	List<Buffet> findAllByOrderByNomeAsc();
	
	List<Buffet> findAllByOrderByNomeDesc();
	

}
