package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import com.example.demo.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

	Optional<Utente> findByEmail(String email);
	boolean existsByEmail(String email);
	Utente[] findAllByEmail(String email);

}
