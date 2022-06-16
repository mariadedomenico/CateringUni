package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Amministratore;
import com.example.demo.repository.AmministratoreRepository;

@Service
public class AmministratoreService {
	@Autowired
    protected AmministratoreRepository amministratoreRepository;

    
    public Amministratore getAdmin(Long id) {
        Optional<Amministratore> result = this.amministratoreRepository.findById(id);
        return result.orElse(null);
    }

    
    @Transactional
    public Amministratore saveUser(Amministratore amministratore) {
        return this.amministratoreRepository.save(amministratore);
    }
}
