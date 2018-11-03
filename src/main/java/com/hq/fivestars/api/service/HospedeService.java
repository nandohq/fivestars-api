package com.hq.fivestars.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hq.fivestars.api.model.Hospede;
import com.hq.fivestars.api.repository.HospedeRepository;

@Service
public class HospedeService {
	
	@Autowired private HospedeRepository hospedeRepository;
	
	public Hospede atualizar(Long id, Hospede hospede) {
		Hospede existente = hospedeRepository.findOne(id);
		
		if(existente == null) throw new EmptyResultDataAccessException(1);		
		BeanUtils.copyProperties(hospede, existente, "id");
		
		return hospedeRepository.save(existente);
	}
}
