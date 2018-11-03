package com.hq.fivestars.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hq.fivestars.api.event.CreatedResourceEvent;
import com.hq.fivestars.api.model.Hospede;
import com.hq.fivestars.api.repository.HospedeRepository;
import com.hq.fivestars.api.repository.filter.HospedeFilter;
import com.hq.fivestars.api.service.HospedeService;

@RestController
@RequestMapping("/hospedes")
public class HospedeResource {
	
	@Autowired private HospedeRepository hospedeRepository;
	@Autowired private HospedeService hospedeService;
	
	@Autowired private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Hospede> pesquisar(HospedeFilter filtro){		
		return hospedeRepository.filtrar(filtro);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hospede> porId(@PathVariable Long id) {
		Hospede retornado = hospedeRepository.findOne(id);
		return retornado != null ? ResponseEntity.ok(retornado) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Hospede> salvar(@Valid @RequestBody Hospede hospede, HttpServletResponse response) {
		
		Hospede retornado = hospedeRepository.save(hospede);		
		publisher.publishEvent(new CreatedResourceEvent(this, response, retornado.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(retornado);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hospede> atualizar(@PathVariable Long id, @Valid @RequestBody Hospede hospede){
		Hospede existente = hospedeService.atualizar(id, hospede);		
		return ResponseEntity.ok(existente);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		hospedeRepository.delete(id);
	}
}